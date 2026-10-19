package com.example.demo.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	// 200 OK + body；找不到則 404 Not Found
	// .map .orElse 這兩者是 Java 8 引入的 java.util.Optional 所提供的方法
    
	/*
     * 1. .map(...)：有值才轉換
	作用： 如果 Optional 裡面有值，就對裡面的物件執行指定的轉換邏輯，
	並把結果重新包裝成新的 Optional；如果是空的，則什麼都不做，直接回傳一個空的 Optional。
     
     *ResponseEntity::ok 是方法參考（Method Reference），等同於 Lambda：
     *.map(user -> ResponseEntity.ok(user))
     *
     *若查無資料（即 Optional.empty()），.map() 會直接跳過處理，維持空的狀態。
     *若查到 user，將它包裝成 HTTP 200 回應物件 ResponseEntity.ok(user)，此時會變成 Optional<ResponseEntity<User>>。
     *
     *ResponseEntity.notFound().build() 會建立一個 HTTP 狀態碼為 404 Not Found、且沒有 body 的回應物件。
		邏輯說明：
		前一步的 .map() 若有包裝出 ResponseEntity.ok(user)，.orElse 就直接將它回傳。
		前一步若依然是空的（代表找不到該 ID），則回傳 404 的 ResponseEntity。
     *
     *傳統寫法:
     *@GetMapping("/{id}")
		public ResponseEntity<User> getById(@PathVariable String id) {
    		Optional<User> optionalUser = userService.getUserById(id);

    		if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        return ResponseEntity.ok(user); // 200 OK + body
    		} else {
        return ResponseEntity.notFound().build(); // 404 Not Found
    		 }
		}
		
	 *使用 .map().orElse() 能省去手動判斷 isPresent() 與 null 檢查，讓程式碼更精簡流暢。
     
     *補充:
     * 值可能為 null（若為 null 會自動轉為 Optional.empty()）
		String name = null;
		Optional<String> opt2 = Optional.ofNullable(name);
		這兩行程式碼做的事情，白話來說就是：
		「請幫我拿一個盒子（opt2）。」
		「如果 name 裡面有字串（例如 "Tom"），就把它放進盒子裡。」
		「如果 name 是 null，就給我一個乾淨的空盒子（也就是 Optional.empty()），不要讓它變成危險的 null。」
     */
	
	
	@Autowired
	UserService userService;
	
	//{"id":"B","name":"Bob"}
	// 200 OK + body；找不到則 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)                    // 200 + body
                .orElse(ResponseEntity.notFound().build()); // 404
        			
    }
    
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user)
    {
    	//.created(URI.create("/users/" + saved.getId()))URI可以略寫如下
    		User saved = userService.create(user);
    		return ResponseEntity
    				.created(URI.create("/users/" + saved.getId()))
    				.body(saved);
    }
    
    //ResponseEntity<Void> 代表「這個 HTTP 回應只包含狀態碼與標頭（Header），完全沒有回應主體（Response Body）」。
    
    /*
     * 在 Java 中，ResponseEntity<T> 是一個泛型類別（Generic Class），角括號 <T> 裡面只能放「物件型態（Reference Type）」，不能放基本型態或關鍵字（如 int、double、void）。
     *java.lang.Void 是 Java 內建的包裝型態，專門用來在泛型中表示「這裡沒有任何回傳物件」。
     */
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
    	return userService.delete(id)
                ? ResponseEntity.noContent().build()   // 204
                : ResponseEntity.notFound().build();   // 404
    }
}
