## 1.建立forSourceTree with github：

1.安裝SourceTree，第一個選單跳過，一直下一步直到結束

## 2：設定 GitHub 個人存取權杖（PAT）
1. **在 GitHub 建立 PAT**：
   - 登入 GitHub → 右上角個人圖示 → Settings
   - 左側選單：Developer settings → Personal access tokens → Tokens (classic)
   - 點擊「Generate new token」→「Generate new token (classic)」
   - Note：`SourceTree Access`
   - Expiration：選擇有效期限（建議 90 days）
   - 勾選 Scopes：`repo`（完整存取倉庫）、`workflow`（選用）
   - 點擊「Generate token」→ **立即複製 Token**（離開頁面後無法再查看）

2. **在 SourceTree 加入 GitHub 帳號**：
   - SourceTree 上方選單：Tools → Options
   - 切換到「Authentication」分頁
   - 點擊「Add」按鈕
   - 點擊「OK」