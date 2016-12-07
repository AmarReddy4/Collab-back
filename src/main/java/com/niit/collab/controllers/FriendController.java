package com.niit.collab.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collab.dao.FriendDAO;
import com.niit.collab.dao.UsersDAO;
import com.niit.collab.model.Friend;
import com.niit.collab.model.Users;

@RestController
public class FriendController {
@Autowired
private FriendDAO friendDAO;
@Autowired
private UsersDAO usersDAO;
@Autowired
private Friend friend;
@Autowired
HttpSession session;


private Users users;

private List<Friend> Users;
 
@RequestMapping(value="/sendrequest/{fid}",method=RequestMethod.POST)

public void sendrequest(@PathVariable("fid") String fid,HttpSession session)
{
	
	String uid=(String) session.getAttribute("username");
	friend.setUserid(uid);
	friend.setFriendid(fid);
	friend.setStatus('n');
	friendDAO.save(friend);
	/*return new ResponseEntity<Friend>(friend,HttpStatus.OK); */
}

/*@PostMapping("/sendrequest/{fid}")
public void sendfrndrequest(@PathVariable("fid") String fid,HttpSession session)
{
	String uid=(String) session.getAttribute("uid");
	friend.setUserid(uid);
	
	friend.setStatus('n');
	friendDAO.saveOrUpdate(friend);
	//User user=userDAO.userbyid(fid);
	//user.setStatus("Y");
	//userDAO.saveOrUpdate(user);
}*/
/*@PostMapping(value="/sendrequest/{fid}")
public ResponseEntity<Friend> newfriend(Friend friend,@PathVariable("fid") int fid,HttpSession session)
{
	friend.setFriendid(fid);
	int uid = (Integer) session.getAttribute("uid");
	friend.setUserid(uid);
	friend.setStatus('n');
	friendDAO.saveOrUpdate(friend);
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
}*/

@GetMapping(value="/myfriends")
public ResponseEntity<List<Friend>> myfriends(HttpSession session){
	String uid = (String) session.getAttribute("username");
	//List<Users> u1 = null;
	List<Friend> list=friendDAO.getfriendlist(uid);
/*	System.out.println(list.size());
	for(int i=0;i < list.size();i++){
		System.out.println(i);
		Friend friends = list.get(i);
		System.out.println("friend");
		 int f = friends.getFrreinfid();
		u1= usersDAO.getuser(f);
*/	    
		
	return new ResponseEntity<List<Friend>>(list,HttpStatus.OK);

}
@PostMapping(value="/acceptrequest/{fid}")
public ResponseEntity<Friend> acceptFriendFriendRequest(@PathVariable("fid")String fid, HttpSession session) {

	String uid=(String)session.getAttribute("username");
	Friend friend=friendDAO.acceptfriend(fid, uid);
	/*saveOrUpdate(fid,"A");*/
	friend.setStatus('a');
	friendDAO.Update(friend);
	Friend friend1=new Friend();
	friend1.setUserid(uid);
	friend1.setFriendid(fid);
	friend1.setStatus('a');
	friendDAO.save(friend1);

	return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	
}
/*public ResponseEntity<Friend> acceptfriend(HttpSession session){
	int id = (Integer) session.getAttribute("uid");
	Friend friend =friendDAO.newrequest(id);
	friend.setStatus('a');
	friendDAO.saveOrUpdate(friend);
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);*/
private void Update(String fid, String string) {
	// TODO Auto-generated method stub
	
}

@PostMapping(value="/rejectrequest/{uid}")
public ResponseEntity<Friend> rejectfriend(@PathVariable("uid") String uid,HttpSession session){
	String id = (String) session.getAttribute("username");
	Friend friend =friendDAO.acceptfriend(uid,id);
	Friend friend1=friendDAO.acceptfriend(id, uid);
	friend.setStatus('r');
	friend1.setStatus('r');
	friendDAO.Update(friend);
	friendDAO.Update(friend1);
	return new ResponseEntity<Friend>(HttpStatus.OK);
}
}