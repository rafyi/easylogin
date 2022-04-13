package com.example.easylogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.example.easylogin.model.dao.UserRepository;
import com.example.easylogin.model.entity.User;

@Controller
public class LoginController {
	@Autowired
	// これを付与したフィールドはnew使わなくてもインスタンス化される
	UserRepository userRepos;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, Model m) {
		/*
		 * Stringデータを戻り値で返すloginメソッド 引数は3つ、最初の2つにparamアノテーション
		 * param付はクライアントからのリクエストなことを意味 HTML側で定義されたname属性を指定することで判断
		 * modelはレスポンスとしてクライアントに返す為のオブジェクト addatributeメソッドでmessageというキー文字列に対して
		 * ログイン結果によって分岐するメッセージを値に設定
		 */

		String message = "welcome!";
		List<User> users = userRepos.findByUserNameAndPassword(userName, password);
		// UserReposのメソッドからUserの一覧を取得
		if (users.size() > 0) {
			User user = users.get(0);
			message += user.getFullName();
		} else {
			message += "guest";
		}
		m.addAttribute("message", message);
		return "login";
	}

	/*
	 * @RequestMapping("/")
	 * 
	 * @ResponseBody public String showUsers() { List<User>
	 * users=userRepos.findAll(); UserRepositoryのインスタンスからfindAllメソッドを呼び出し、
	 * Userエンティティのリストを取得している FindAllメソッドの戻り値のデータ型がListだからListで取得 User
	 * user=users.get(0); //上で取得したリストの0番目を取得 String
	 * info=user.getUserName()+""+user.getPassword(); //Userエンティティのインスタンスから
	 * //UserNameとpasswordを連結した文字列を作成 return info; }
	 */}
