import { useState, useEffect } from 'react';

function MemberList() {
  const [members, setMembers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

  // 編輯控制狀態
  const [editingId, setEditingId] = useState(null);
  const [editFormData, setEditFormData] = useState({
    memberId: null,
    name: '',
    username: '',
    phone: '',
    email: '',
    password: ''
  });

  // 取得分頁會員資料
  const fetchData = async () => {
    try {
      setLoading(true);
      setError(null);
      const response = await fetch(`http://localhost:8080/api/member?page=${page}&size=10`);

      if (!response.ok) {
        throw new Error(`HTTP 錯誤！狀態碼：${response.status}`);
      }

      const result = await response.json();
      setMembers(result.content || []);
      setTotalPages(result.totalPages || 1);
    } catch (err) {
      setError(err.message);
      console.error('Fetch error:', err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, [page]);

  // 分頁切換
  const handlePrevPage = () => {
    if (page > 1) setPage((prev) => prev - 1);
  };

  const handleNextPage = () => {
    if (page < totalPages) setPage((prev) => prev + 1);
  };

  // 點擊 Edit
  const handleEditClick = (item) => {
    setEditingId(item.memberId);
    setEditFormData({
      memberId: item.memberId,
      name: item.name || '',
      username: item.username || '',
      phone: item.phone || '',
      email: item.email || '',
      password: '' // 密碼預設空白，不改就不填
    });
  };

  // 即時監聽輸入
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditFormData((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  // 取消編輯
  const handleCancelClick = () => {
    setEditingId(null);
  };

  // 送出 PUT 請求修改
  const handleSavePut = async (memberId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/member/${memberId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(editFormData)
      });

      // 攔截 409 衝突
      if (response.status === 409) {
        const errorData = await response.json().catch(() => null);
        console.warn('409 衝突錯誤：', errorData);
        console.log('HTTP 狀態碼 (response.status):', response.status);
        console.log('完整後端錯誤物件 (errorData):', errorData) // errorData是null
        alert(errorData?.message || errorData?.reason || '資料衝突：帳號或 Email 已被其他會員使用！');
        return;
      }

      if (!response.ok) {
        throw new Error(`更新失敗！狀態碼：${response.status}`);
      }

      alert('更新成功！');
      setEditingId(null);
      fetchData(); // 重新向後端取得最新資料清單
    } catch (err) {
      console.error('連線異常：', err);
      alert(`發生錯誤：${err.message}`);
    }
  };

  if (loading) return <p style={{ padding: '20px' }}>載入中...</p>;
  if (error) return <p style={{ padding: '20px', color: 'red' }}>錯誤訊息：{error}</p>;

  return (
    <div style={{ padding: '20px', fontFamily: 'sans-serif' }}>
      <h2>會員列表</h2>
      <table border="1" cellPadding="8" style={{ borderCollapse: 'collapse', width: '100%' }}>
        <thead>
          <tr style={{ backgroundColor: '#f2f2f2' }}>
            <th>編號</th>
            <th>姓名</th>
            <th>帳號</th>
            <th>電話</th>
            <th>Email</th>
            <th>新密碼</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          {members.map((item) => {
            const isEditing = editingId === item.memberId;

            return (
              <tr key={item.memberId}>
                {/* 編號 (唯讀) */}
                <td>{item.memberId}</td>

                {/* 姓名 */}
                <td>
                  {isEditing ? (
                    <input
                      type="text"
                      name="name"
                      value={editFormData.name}
                      onChange={handleInputChange}
                    />
                  ) : (
                    item.name
                  )}
                </td>

                {/* 帳號 */}
                <td>
                  {isEditing ? (
                    <input
                      type="text"
                      name="username"
                      value={editFormData.username}
                      onChange={handleInputChange}
                    />
                  ) : (
                    item.username
                  )}
                </td>

                {/* 電話 */}
                <td>
                  {isEditing ? (
                    <input
                      type="text"
                      name="phone"
                      value={editFormData.phone}
                      onChange={handleInputChange}
                    />
                  ) : (
                    item.phone
                  )}
                </td>

                {/* Email */}
                <td>
                  {isEditing ? (
                    <input
                      type="email"
                      name="email"
                      value={editFormData.email}
                      onChange={handleInputChange}
                    />
                  ) : (
                    item.email
                  )}
                </td>

                {/* 新密碼 */}
                <td>
                  {isEditing ? (
                    <input
                      type="password"
                      name="password"
                      placeholder="不修改請留空"
                      value={editFormData.password}
                      onChange={handleInputChange}
                    />
                  ) : (
                    <span style={{ color: '#aaa' }}>******</span>
                  )}
                </td>

                {/* 操作按鈕 */}
                <td>
                  {isEditing ? (
                    <>
                      <button
                        onClick={() => handleSavePut(item.memberId)}
                        style={{ marginRight: '6px' }}
                      >
                        儲存
                      </button>
                      <button onClick={handleCancelClick}>取消</button>
                    </>
                  ) : (
                    <button onClick={() => handleEditClick(item)}>Edit</button>
                  )}
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>

      {/* 分頁按鈕控制區 */}
      <div style={{ marginTop: '16px', display: 'flex', alignItems: 'center', gap: '12px' }}>
        <button onClick={handlePrevPage} disabled={page <= 1}>
          上一頁
        </button>

        <span>
          第 <strong>{page}</strong> 頁 / 共 <strong>{totalPages}</strong> 頁
        </span>

        <button onClick={handleNextPage} disabled={page >= totalPages}>
          下一頁
        </button>
      </div>
    </div>
  );
}

export default MemberList;