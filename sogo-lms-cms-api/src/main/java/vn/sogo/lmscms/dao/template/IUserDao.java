package vn.sogo.lmscms.dao.template;

import java.util.List;

import vn.sogo.lmscms.model.User;

public interface IUserDao {
	public boolean addEntity(User user) throws Exception;
	public User getEntityById(long id) throws Exception;
	public List<User> getEntityList() throws Exception;
	public boolean deleteEntity(long id) throws Exception;
}