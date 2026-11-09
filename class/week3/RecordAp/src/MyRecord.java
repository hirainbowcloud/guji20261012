
public class MyRecord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GreetingResponse g1=new GreetingResponse("Hello",System.currentTimeMillis());
		System.out.println(g1.message());
		System.out.println(g1.timestamp());
		System.out.println(g1.toString());
	}

}

//record：不可變的資料载体（Java 16+），API 回傳結構化資料時很實用
record GreetingResponse(String message, long timestamp) {}