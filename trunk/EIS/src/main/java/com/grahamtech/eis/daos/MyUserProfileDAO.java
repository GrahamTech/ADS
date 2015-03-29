package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;
import com.grahamtech.eis.pojos.UserProfile;

public class MyUserProfileDAO extends AbstractDAO<UserProfile> {
  // private Session session;

  public MyUserProfileDAO(SessionFactory sessionFactory) {
    super("UserProfile", "user_profile_id", UserProfile.class);
    // super("user_profile", "user_profile_id", UserProfile.class);
    setSessionFactory(sessionFactory);
    // this.session = sessionFactory.openSession();
  }

  /*
   * final Criteria criteria = getSession(false).createCriteria(MyObject.class);
   * criteria.createAlias("myObjectsReferenceEntityName", "tableLink1");
   * criteria.add(Restrictions.eq("tableLink1.myField2", 1));
   * criteria.add(Restrictions.isNotNull("field1"));
   * criteria.setProjection(Projections.rowCount()); final List<MyObject>
   * myObjects = criteria.list();
   */

  // // @Override
  // public void addUser(UserType userMap) {
  // getHibernateTemplate().save(userMap);
  // }
  //
  // // @Override
  // public List<UserProfile> findAll() {
  // return (List<UserProfile>)
  // getHibernateTemplate().find("from user_profile");
  // }
  //
  // @Override
  // public void deleteUser(UserType user) {
  // getHibernateTemplate().delete(user);
  // }
  //
  // @Override
  // public void updateUser(UserType user) {
  // getHibernateTemplate().update(user);
  // }

  // @Transactional
  // public List<UserProfile> findAll() {
  // @SuppressWarnings("unchecked")
  // List<UserProfile> listUser =
  // (List<UserProfile>) sessionFactory.getCurrentSession()
  // .createCriteria(UserProfile.class)
  // .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
  //
  // return listUser;
  // }

  // public MyUserProfileDAO() {
  // super("MyUserProfileObject", "id", UserProfile.class);
  // }
}
