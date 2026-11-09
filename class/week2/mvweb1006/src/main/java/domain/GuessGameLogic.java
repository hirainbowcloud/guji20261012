package domain;

/**
 * 猜數字遊戲的業務邏輯類別
 * 這個 JavaBean 負責處理遊戲的核心邏輯
 * 
 * @author 程式開發教學團隊
 * @version 1.0
 */
public class GuessGameLogic {
    // 私有屬性：封裝遊戲狀態
    private int theNumber;          // 要猜的數字
    private int remainder = 5;     // 剩餘猜測次數
    private String hint;            // 提示訊息
    
    /**
     * 預設建構子  default Constructor
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
        return startNumber + (int) (Math.random() * range);
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
