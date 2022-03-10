package com.tweetapp.tweet_app_console;

import java.util.Map;
import java.util.Scanner;

import com.tweetapp.tweet_app_console.utils.RegisterUtil;

/**
 * @author Vedant09
 *
 */
public class App {
	public static boolean loggedInStatus = false;
	public static int userId;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		RegisterUtil userUtil = new RegisterUtil();
		
		if (!loggedInStatus) {
			System.out.println("Welcome to Tweet App\n \n Please choose from the options below:\n");
			System.out.println("\n1.Register\n2.Login\n3.Forgot password");

			int option = scanner.nextInt();
			switch (option) {
			case 1:
				userUtil.registerUser();
				main(args);
				break;
			case 2:
				Map<String, Integer> res = userUtil.login();
				if (res.get("status") == 1) {
					loggedInStatus = true;
					userId = res.get("userId");
				} else {
					System.out.println("Invalid email or password");
				}
				main(args);
				break;

			case 3:
				userUtil.forgotPassword();
				main(args);
				break;
			default:
				System.out.println("Invalid Option. Please try again..");
				main(args);

			}
		} else {
			System.out.println("Logged in successfully.. Please choose from the options below:\n");
			System.out.println(
					"____________________________________________\n1.Post a tweet\n2.View my tweets\n3.View all tweets\n4.View all users\n5.Reset password\n6.Logout");

			int option = scanner.nextInt();
			switch (option) {
			case 1:
				userUtil.createTweet(userId);
				main(args);
				break;
			case 2:
				userUtil.getMyTweets(userId);
				main(args);
				break;
			case 3:
				userUtil.getAllTweets();
				main(args);
				break;
			case 4:
				userUtil.getAllUsers();
				main(args);
				break;
			case 5:
				userUtil.updateUser(userId);
				main(args);
				break;
			case 6:
				if (userUtil.logout(userId)) {
					loggedInStatus = false;
				}
				;
				main(args);
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				main(args);

			}
		}
	}
}
