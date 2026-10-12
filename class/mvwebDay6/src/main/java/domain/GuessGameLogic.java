package domain;

public class GuessGameLogic 
{
	// 私有屬性：封裝遊戲狀態
    private int theNumber;          // 要猜的數字
    private int remainder = 10;     // 剩餘猜測次數
    private String hint;            // 提示訊息
    
    /**
     * 預設建構子
     */
    public GuessGameLogic() {
        // 空的建構子，讓 JSP 能夠實例化這個類別
    }
    
    /**
     * 初始化遊戲
     * @param startNumber 範圍起始數字
     * @param endNumber 範圍結束數字
     */
    public void initialize(int startNumber, int endNumber) {
        this.theNumber = generateRandomNumber(startNumber, endNumber);
        this.remainder = 10; // 重置猜測次數
        this.hint = ""; // 清除提示
    }
    
    /**
     * 檢查猜測是否正確
     * @param guess 用戶猜測的數字
     * @return true 如果猜中，false 如果沒猜中
     */
    public boolean isCorrectGuess(int guess) {
        if (guess == theNumber) {
            return true;
        } else { 
            // 沒猜中，提供提示並減少剩餘次數
            if (guess > theNumber) {
                hint = "您猜的數字太大了！試試更小的數字 🔽";       
            } else {
                hint = "您猜的數字太小了！試試更大的數字 🔼";       
            }
            remainder--;
            return false;
        }
    }
    
    /**
     * 產生指定範圍內的隨機數字
     * @param startNumber 範圍起始數字
     * @param endNumber 範圍結束數字
     * @return 隨機產生的數字
     */
    private int generateRandomNumber(int startNumber, int endNumber) {
        double range = (double) (endNumber - startNumber + 1);
        
        /*
         * 在計算「包含兩端點」的整數個數時，公式為 最大值 - 最小值 + 1。
		   例如：若要產生 1~10 之間的隨機數，個數為 10 - 1 + 1 = 10 個整數。
         */
        return startNumber + (int) (Math.random() * range);
        /*
         * 為什麼要強制轉型為 (double)？
		   將結果轉為浮點數（double），是為了後續與 Math.random() 進行浮點數乘法運算時保持精確度。
         
         *Math.random()：
		  Java 原生的隨機函數，會回傳一個介於 0.0（包含）到 1.0（不包含）之間的 double 隨機浮點數
         *將隨機數放大到目標區間長度。乘上 range 後，數值範圍變如0*10=0 1*10=10 ,0<= Math.random() < 1 範圍0~0.99
         *int,強制轉型 無條件捨棄
         */
        
    }
    
    /**
     * 取得剩餘猜測次數
     * @return 剩餘次數
     */
    public int getRemainder() {
        return remainder;
    }
    
    /**
     * 取得提示訊息
     * @return 提示字串
     */
    public String getHint() {
        return hint;
    }
}
