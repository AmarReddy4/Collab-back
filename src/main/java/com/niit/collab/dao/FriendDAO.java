package com.niit.collab.dao;

import java.util.List;

import com.niit.collab.model.*;

public interface FriendDAO
{

	public boolean save(Friend friend);
	public boolean Update(Friend friend);
	public boolean Delete(Friend friend);
	public Friend getFriend(String id);
	public Friend newrequest(String id);
	public Friend acceptfriend(String uid, String fid);
	
	public List<Friend> list();
	public List<Friend> getfriendlist(String uid);
	public Friend UpdateStatus(String uid,String fid);
	
	
}