
public class MyRecord 
{

	public static void main(String[] args) 
	{
        GreetingResponse g1 = new GreetingResponse("HI HI", System.currentTimeMillis()); 
        
        System.out.println(g1.message()); 
        System.out.println(g1.timestamp()); 
        System.out.println(g1.toString()); 
	}
}

/// record：不可變的資料載體（Java 16+），API 回傳結構化資料時很實用 
record GreetingResponse(String message, long timestamp) {}