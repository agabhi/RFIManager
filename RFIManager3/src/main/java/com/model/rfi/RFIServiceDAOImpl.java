package com.model.rfi;

import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContextHolder;

import com.springmvc.rfi.UserRegistrationForm;

public class RFIServiceDAOImpl implements RFIServiceDAO {
	
	private Session getSession()
	{
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}
	
	@Override
	public List<WorkItem> getItemsList() {
		Session session = getSession();
		List<WorkItem> workItems = null;
		try{
			
			workItems = session.createCriteria(WorkItem.class).list();
			session.flush();
			return workItems;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<BOQ> getCompleteBoqList()
	{
		Session session = getSession();
		try
		{
			List<BOQ> boqs = session.createCriteria(BOQ.class).list();
			session.flush();
			return boqs;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public Map<String, List<BOQ>> getBoqList()
	{
		Session session = getSession();
		try
		{
			Map<String, List<BOQ>> boqMap = new LinkedHashMap<String, List<BOQ>>();
			List categoryList = session.createSQLQuery("select distinct category from boq order by category_sequence asc").list();
			if(categoryList != null)
			{
				Iterator catIterator = categoryList.iterator();
				int i = 1;
				while (catIterator.hasNext())
				{
					String category = (String)catIterator.next();
					List<BOQ> boqs = session.createCriteria(BOQ.class).add( Restrictions.eq("category", category) ).list();
					boqMap.put(i+". "+category,boqs);
					++i;
				}
			}
			
			session.flush();
			return boqMap;
		}
		finally
		{
			session.close();
		}
	}
	
	public List<String> getWorkItemDescriptionList()
	{
		Session session = getSession();
		try
		{
			List<String> workItemDescriptionList = session.createSQLQuery("select item_description from work_item order by item_description asc").list();
			session.flush();
			return workItemDescriptionList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<String> getWorkItemsDescription(String where)
	{
		Session session = getSession();
		try
		{
			List<String> workItemDescriptionList = session.createSQLQuery("select item_description from work_item "+where+" order by item_description asc").list();
			
			session.flush();
			return workItemDescriptionList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<String> getActivityWorkItemsDescription(String where)
	{
		Session session = getSession();
		try
		{
			System.out.println("select distinct a.item_description FROM (rfi.work_item a inner join rfi.work_item_activities b on (a.work_item_id =b.work_item_id)) "+where+" order by item_description asc");
			List<String> workItemDescriptionList = session.createSQLQuery("select distinct a.item_description FROM (rfi.work_item a inner join rfi.work_item_activities b on (a.work_item_id =b.work_item_id)) "+where+" order by item_description asc").list();
			
			session.flush();
			return workItemDescriptionList;
		}
		finally
		{
			session.close();
		}
	}
	
	
	public List<String> getBoqNumberList(String where)
	{
		Session session = getSession();
		try
		{
			System.out.println("select distinct boq_number from work_item "+where+" order by boq_number asc");
			List<String> boqNumberList = session.createSQLQuery("select distinct boq_number from work_item "+where+" order by boq_number asc").list();
			
			session.flush();
			return boqNumberList;
		}
		finally
		{
			session.close();
		}
	}
	
	public List<String> getActivityBoqNumberList(String where)
	{
		Session session = getSession();
		try
		{
			System.out.println("select distinct a.boq_number FROM (rfi.work_item a inner join rfi.work_item_activities b on (a.work_item_id =b.work_item_id)) "+where+" order by boq_number asc");
			List<String> boqNumberList = session.createSQLQuery("select distinct a.boq_number FROM (rfi.work_item a inner join rfi.work_item_activities b on (a.work_item_id =b.work_item_id)) "+where+" order by boq_number asc").list();
			
			session.flush();
			return boqNumberList;
		}
		finally
		{
			session.close();
		}
	}
	
	
	
	@Override
	public List<String> getCategoryList() {
		Session session = getSession();
		try
		{
			List<String> categoryList = session.createSQLQuery("select distinct category from boq order by category asc").list();
			
			session.flush();
			return categoryList;
		}
		finally
		{
			session.close();
		}
	}
	
	
	public List<String> getBoqNumberList()
	{
		Session session = getSession();
		try
		{
			List<String> boqNumberList = session.createSQLQuery("select boq_number from boq order by boq_number asc").list();
			
			session.flush();
			return boqNumberList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<String> getUnitsList() {
		Session session = getSession();
		try
		{
			Map<String, List<BOQ>> boqMap = new LinkedHashMap<String, List<BOQ>>();
			List<String> unitsList = session.createSQLQuery("select distinct unit from boq order by unit asc").list();
			
			session.flush();
			return unitsList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<Side> getSideList() {
		
		Session session = getSession();
		try
		{
			List<Side> sideList = session.createSQLQuery("select side, side_description from side order by side").addEntity(Side.class).list();
			session.flush();
			return sideList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<Activity> getActivityList() {
		
		Session session = getSession();
		try
		{
			List<Activity> activityList = session.createSQLQuery("select activity from activity order by activity").addEntity(Activity.class).list();
			session.flush();
			return activityList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<Layer> getLayerList() {
		
		Session session = getSession();
		try
		{
			List<Layer> layerList = session.createSQLQuery("select layer from layer order by convert(layer,SIGNED), layer").addEntity(Layer.class).list();
			session.flush();
			return layerList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<Grade> getGradeList() {
		
		Session session = getSession();
		try
		{
			List<Grade> gradeList = session.createSQLQuery("select grade from grade order by grade").addEntity(Grade.class).list();
			session.flush();
			return gradeList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<RFICode> getRfiCodeList() {
		
		Session session = getSession();
		try
		{
			List<RFICode> rfiCodeList = session.createCriteria(RFICode.class).list();
			session.flush();
			return rfiCodeList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<Status> getStatusList() {
		
		Session session = getSession();
		try
		{
			List<Status> statusList = session.createCriteria(Status.class).list();
			session.flush();
			return statusList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<Bill> getBillList() {
		
		Session session = getSession();
		try
		{
			List<Bill> billList = session.createCriteria(Bill.class).list();
			session.flush();
			return billList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public Integer getNextRfiNumber() {
		List<Integer> rfiNumberList;
		
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			rfiNumberList = session.createSQLQuery("select counter from rfi_sequence").list();
			session.flush();
			return rfiNumberList.get(0);
		}
		finally
		{
			session.close();
		}
		
	}
	
	public BigInteger getStatusCount(String status, String where)
	{
		List<BigInteger> statusCountList;
		
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			statusCountList = session.createSQLQuery("select count(*) from rfi" + where+" AND status = '"+status+"' ").list();
			session.flush();
			return statusCountList.get(0);
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public RFI add(RFI rfi)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			int add = 0;
			if(rfi.getRfiNumber() == null) {
				List<Integer> rfiNumberList = session.createSQLQuery("select counter from rfi_sequence").list();
				String rfiNumber = rfiNumberList.get(0)+"";
				rfi.setRfiNumber(rfiNumber);
				rfi.setCreatedByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
				rfi.setLastEditedByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
				add = 1;
			}
			
			session.save(rfi);
			if(add == 1) {
				session.createSQLQuery("update rfi_sequence set counter = counter+1").executeUpdate();
			}
			session.getTransaction().commit();
			session.flush();
			return rfi;
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void createNewUser(String role, String username, String firstName, String lastName, String password)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			session.createSQLQuery("insert into users values('"+username.trim()+"', '"+password.trim()+"', b'1','"+firstName.trim()+"','"+lastName.trim()+"')").executeUpdate();
			session.createSQLQuery("insert into authorities values('"+username.trim()+"', '"+role.trim()+"')").executeUpdate();
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void addBoq(BOQ boq) {
		
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			session.save(boq);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void addQuantity(Quantity quantity)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			session.save(quantity);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void addWorkItem(WorkItem workItem)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			session.save(workItem);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void editWorkItem(WorkItem workItem)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<WorkItem> workItemList = session.createCriteria(WorkItem.class)
					.add(Restrictions.eq("itemDescription",workItem.getItemDescription() ))
					.list();
			WorkItem originalWorkItem = workItemList.get(0);
			if(originalWorkItem.getItemDescription().equals(workItem.getItemDescription()))
			{
				originalWorkItem.setBoqNumber(workItem.getBoqNumber());
				originalWorkItem.setLayers(workItem.getLayers());
				originalWorkItem.setSides(workItem.getSides());
				originalWorkItem.setActivities(workItem.getActivities());
				originalWorkItem.setSequence(workItem.getSequence());
				session.saveOrUpdate(originalWorkItem);
			}
			
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void deleteWorkItem(WorkItem workItem) 
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<WorkItem> workItemList = session.createCriteria(WorkItem.class)
					.add(Restrictions.eq("itemDescription",workItem.getItemDescription() ))
					.list();
			WorkItem originalWorkItem = workItemList.get(0);
			session.delete(originalWorkItem);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	
	@Override
	public void addStatus(Status status) {
		
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			session.save(status);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void deleteStatus(Status status) {
		
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Status> statusList = session.createCriteria(Status.class)
					.add(Restrictions.eq("status",status.getStatus() ))
					.list();
			Status originalStatus = statusList.get(0);
		
			session.delete(originalStatus);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void editStatus(String oldStatus, String newStatus)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Status> statusList = session.createCriteria(Status.class)
					.add(Restrictions.eq("status",oldStatus ))
					.list();
			Status originalStatus = statusList.get(0);
			
			Status status = new Status();
			status.setStatus(newStatus);
			session.save(status);
			
			session.createSQLQuery("update rfi set status = '"+newStatus.trim()+"' where status = '"+oldStatus.trim()+"'").executeUpdate();
			
			session.delete(originalStatus);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	
	@Override
	public void addRFICode(RFICode rfiCode)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			session.save(rfiCode);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void deleteRFICode(RFICode rfiCode)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<RFICode> rfiCodeList = session.createCriteria(RFICode.class)
					.add(Restrictions.eq("rfiCode",rfiCode.getRfiCode() ))
					.list();
			RFICode originalRFICode = rfiCodeList.get(0);
		
			session.delete(originalRFICode);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void editRFICode(String oldRFICode, String newRFICode)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<RFICode> rfiCodeList = session.createCriteria(RFICode.class)
					.add(Restrictions.eq("rfiCode",oldRFICode ))
					.list();
			RFICode originalRFICode = rfiCodeList.get(0);
			
			RFICode rfiCode = new RFICode();
			rfiCode.setRfiCode(newRFICode);
			session.save(rfiCode);
			
			session.createSQLQuery("update rfi set rfi_code = '"+newRFICode.trim()+"' where rfi_code = '"+oldRFICode.trim()+"'").executeUpdate();
			
			session.delete(originalRFICode);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	
	
	@Override
	public void addSide(Side side)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			session.save(side);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void addActivity(Activity activity)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			session.save(activity);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void deleteSide(Side side) {
		
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Side> sideList = session.createCriteria(Side.class)
					.add(Restrictions.eq("side",side.getSide() ))
					.list();
			Side originalSide = sideList.get(0);
		
			session.delete(originalSide);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void deleteActivity(Activity activity) {
		
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Activity> activityList = session.createCriteria(Activity.class)
					.add(Restrictions.eq("activity",activity.getActivity() ))
					.list();
			Activity originalActivity = activityList.get(0);
		
			session.delete(originalActivity);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void editSide(String oldSide, String newSide)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Side> sideList = session.createCriteria(Side.class)
					.add(Restrictions.eq("side",oldSide ))
					.list();
			Side originalSide = sideList.get(0);
			
			Side side = new Side();
			side.setSide(newSide);
			session.save(side);
			
			session.createSQLQuery("update rfi set side = '"+newSide.trim()+"' where side = '"+oldSide.trim()+"'").executeUpdate();
			
			session.delete(originalSide);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void editActivity(String oldActivity, String newActivity)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Activity> activityList = session.createCriteria(Activity.class)
					.add(Restrictions.eq("activity",oldActivity ))
					.list();
			Activity originalActivity = activityList.get(0);
			
			Activity activity = new Activity();
			activity.setActivity(newActivity);
			session.save(activity);
			
			session.createSQLQuery("update rfi set activity = '"+newActivity.trim()+"' where activity = '"+oldActivity.trim()+"'").executeUpdate();
			
			session.delete(originalActivity);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	
	@Override
	public void addLayer(Layer layer)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			session.save(layer);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void deleteLayer(Layer layer)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Layer> layerList = session.createCriteria(Layer.class)
					.add(Restrictions.eq("layer",layer.getLayer() ))
					.list();
			Layer originalLayer = layerList.get(0);
		
			session.delete(originalLayer);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void editLayer(String oldLayer, String newLayer)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Layer> layerList = session.createCriteria(Layer.class)
					.add(Restrictions.eq("layer",oldLayer ))
					.list();
			Layer originalLayer = layerList.get(0);
			
			Layer layer = new Layer();
			layer.setLayer(newLayer);
			session.save(layer);
			
			session.createSQLQuery("update rfi set layer = '"+newLayer.trim()+"' where layer = '"+oldLayer.trim()+"'").executeUpdate();
			
			session.delete(originalLayer);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	
	
	@Override
	public void addGrade(Grade grade)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			session.save(grade);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void deleteGrade(Grade grade) {
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Grade> gradeList = session.createCriteria(Grade.class)
					.add(Restrictions.eq("grade",grade.getGrade() ))
					.list();
			Grade originalGrade = gradeList.get(0);
		
			session.delete(originalGrade);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void editGrade(String oldGrade, String newGrade) {
		
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Grade> gradeList = session.createCriteria(Grade.class)
					.add(Restrictions.eq("grade",oldGrade ))
					.list();
			Grade originalGrade = gradeList.get(0);
			
			Grade grade = new Grade();
			grade.setGrade(newGrade);
			session.save(grade);
			
			session.createSQLQuery("update rfi set grade = '"+newGrade.trim()+"' where grade = '"+oldGrade.trim()+"'").executeUpdate();
			
			session.delete(originalGrade);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void addBoqList(Set<BOQ> boqHash)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			Iterator<BOQ> boqIt = boqHash.iterator();
			int i = 0;
			while(boqIt.hasNext())
			{
				++i;
				BOQ boq = boqIt.next();
				System.out.println("Adding BOQ number "+i+"\n");
				session.saveOrUpdate(boq);
			}
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void addQuantityList(Set<Quantity> quantityHash)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			Iterator<Quantity> quantityIt = quantityHash.iterator();
			int i = 0;
			while(quantityIt.hasNext())
			{
				++i;
				Quantity quantity = quantityIt.next();
				System.out.println("Adding Quantity number "+i+"\n");
				session.saveOrUpdate(quantity);
			}
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void addLayerExceptionList(Set<LayerException> layerExceptionHash)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			Iterator<LayerException> layerExceptionIt = layerExceptionHash.iterator();
			int i = 0;
			while(layerExceptionIt.hasNext())
			{
				++i;
				LayerException layerException = layerExceptionIt.next();
				System.out.println("Adding Layer Exception number "+i+"\n");
				session.saveOrUpdate(layerException);
			}
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public RFI getRFI(String rfiNumber)
	{
		Session session = getSession();
		try
		{
			List<RFI> rfiList = session.createCriteria(RFI.class)
					.add(Restrictions.eq("rfiNumber",rfiNumber ))
					.list();
			RFI originalRfi = rfiList.get(0);
			
			session.flush();
			return originalRfi;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public BOQ getBoq(String boqNumber)
	{
		Session session = getSession();
		try
		{
			List<BOQ> boqList = session.createCriteria(BOQ.class)
					.add(Restrictions.eq("boqNumber",boqNumber ))
					.list();
			BOQ originalBoq = boqList.get(0);
			
			session.flush();
			return originalBoq;
		}
		finally
		{
			session.close();
		}
	}
	
	
	@Override
	public LayerException getLayerException(Long exceptionId)
	{
		Session session = getSession();
		try
		{
			List<LayerException> layerExceptionList = session.createCriteria(LayerException.class)
					.add(Restrictions.eq("exceptionId",exceptionId ))
					.list();
			LayerException originalLayerException = layerExceptionList.get(0);
			
			session.flush();
			return originalLayerException;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public Quantity getQuantity(Long quantityId)
	{
		Session session = getSession();
		try
		{
			List<Quantity> quantityList = session.createCriteria(Quantity.class)
					.add(Restrictions.eq("quantityId",quantityId ))
					.list();
			Quantity originalQuantity = quantityList.get(0);
			
			session.flush();
			return originalQuantity;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public WorkItem getWorkItem(String itemDescription) {
		
		Session session = getSession();
		try
		{
			List<WorkItem> workItemList = session.createCriteria(WorkItem.class)
					.add(Restrictions.eq("itemDescription",itemDescription ))
					.list();
			System.out.println("Item description is "+itemDescription);
			WorkItem originalWorkItem = workItemList.get(0);
			
			session.flush();
			return originalWorkItem;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public WorkItem getActivityWorkItem(String itemDescription, String activity) {
		
		Session session = getSession();
		try
		{
			List<WorkItem> workItemList = session.createCriteria(WorkItem.class)
					.add(Restrictions.eq("itemDescription",itemDescription ))
					//.createCriteria("activities")
					//.add(Restrictions.eq("activity", activity))
					.list();
			System.out.println("Item description is "+itemDescription);
			WorkItem originalWorkItem = workItemList.get(0);
			
			session.flush();
			return originalWorkItem;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<ExpandedRFI> getMultipleRFI(String[] rfiNumberList) {
		
		Session session = getSession();
		try
		{
			List<ExpandedRFI> expandedRfiList = session.createSQLQuery("select * from rfi_all_details_view where rfi_number in (:list)").addEntity(ExpandedRFI.class).setParameterList("list", rfiNumberList).list();
			session.flush();
			return expandedRfiList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void changeMultipleRFIBillNumber(String[] rfiNumberList, String billNumber)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			session.createSQLQuery("update rfi set bill_number = '"+billNumber.trim()+"' where rfi_number in (:list)").setParameterList("list", rfiNumberList).executeUpdate();
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void deleteRFIFromBill(String[] rfiNumberList)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			session.createSQLQuery("delete from rfi_bill where rfi_number in (:list)").setParameterList("list", rfiNumberList).executeUpdate();
			session.createSQLQuery("update rfi set bill_number = null where rfi_number in (:list)").setParameterList("list", rfiNumberList).executeUpdate();
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void deleteAndAddSides(Set<Side> sideHash)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			Statement st=session.connection().createStatement();
			String sql="delete from side where 1=1";
			st.execute(sql);
			
			Iterator<Side> it = sideHash.iterator();
			while(it.hasNext())
			{
				Side side = it.next();
				System.out.println("Adding side "+side.getSide()+"\n");
				session.save(side);
			}
			session.getTransaction().commit();
			session.flush();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void importRfiHashes(Set<RFI> rfiHash, Set<Side> sideHash, Set<Grade> gradeHash, Set<RFICode> rfiCodeHash, Set<Status> statusHash, Set<Layer> layerHash, Set<BOQ> boqHash, Set<Activity> activityHash, Set<WorkItem> workItemHash) throws Exception
	{
			Session session = getSession();
			try
			{
				session.beginTransaction();
				
				
				Statement st=session.connection().createStatement();
				String sql="delete from rfi_approval where 1=1";
				st.execute(sql);
				sql="delete from rfi where 1=1";
				st.execute(sql);
				
				
				
				Iterator<Side> itSide = sideHash.iterator();
				while(itSide.hasNext())
				{
					Side side = itSide.next();
					System.out.println("Adding Side "+side.getSide()+"\n");
					session.save(side);
				}
				
				Iterator<Activity> itActivity = activityHash.iterator();
				while(itActivity.hasNext())
				{
					Activity activity = itActivity.next();
					System.out.println("Adding Activity "+activity.getActivity()+"\n");
					session.save(activity);
				}
				
				Iterator<Grade> itGrade = gradeHash.iterator();
				while(itGrade.hasNext())
				{
					Grade grade = itGrade.next();
					System.out.println("Adding Grade "+grade.getGrade()+"\n");
					session.save(grade);
				}
				
				Iterator<RFICode> itRfiCode = rfiCodeHash.iterator();
				while(itRfiCode.hasNext())
				{
					RFICode rfiCode = itRfiCode.next();
					System.out.println("Adding RfiCode "+rfiCode.getRfiCode()+"\n");
					session.save(rfiCode);
				}
				
				Iterator<Status> itStatus = statusHash.iterator();
				while(itStatus.hasNext())
				{
					Status status = itStatus.next();
					System.out.println("Adding Side "+status.getStatus()+"\n");
					session.save(status);
				}
				
				Iterator<Layer> itLayer = layerHash.iterator();
				while(itLayer.hasNext())
				{
					Layer layer = itLayer.next();
					System.out.println("Adding Layer "+layer.getLayer()+"\n");
					session.save(layer);
				}
				
				Iterator<BOQ> boqIt = boqHash.iterator();
				while(boqIt.hasNext())
				{
					BOQ boq = boqIt.next();
					System.out.println("Adding BOQ "+boq.getBoqNumber()+"\n");
					session.save(boq);
				}
				
				
				Iterator<WorkItem> workItemIt = workItemHash.iterator();
				while(workItemIt.hasNext())
				{
					WorkItem workItem = workItemIt.next();
					System.out.println("Adding Item "+workItem.getItemDescription()+"\n");
					
					session.saveOrUpdate(workItem);
					
				}
				
				
				
				Iterator<RFI> rfiIt = rfiHash.iterator();
				int i = 0;
				while(rfiIt.hasNext())
				{
					++i;
					RFI rfi = rfiIt.next();
					System.out.println("Adding Rfi number "+i+"\n");
					session.save(rfi);
				}
				
				List<Integer> rfiNumberList = session.createSQLQuery("select max(convert(rfi_number,SIGNED)) from rfi").list();
				String nextRfiNumber = rfiNumberList.get(0)+"";
				session.createSQLQuery("update rfi_sequence set counter = 1+"+nextRfiNumber).executeUpdate();
				
				session.getTransaction().commit();
				session.flush();
			
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				throw new Exception();
				
			}
			finally
			{
				session.close();
				
			}

	}
	
	@Override
	public void deleteAndAddLayers(Set<Layer> layerHash) {
		Session session = getSession();
		try
		{
			session.beginTransaction();
			Statement st=session.connection().createStatement();
			String sql="delete from layer where 1=1";
			st.execute(sql);
			
			Iterator<Layer> it = layerHash.iterator();
			while(it.hasNext())
			{
				Layer layer = it.next();
				System.out.println("Adding Layer "+layer.getLayer()+"\n");
				session.save(layer);
			}
			session.getTransaction().commit();
			session.flush();
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void deleteAndAddWorkItems(Set<WorkItem> workItemHash) 
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			Statement st=session.connection().createStatement();
			String sql="delete from boq_item where 1=1";
			st.execute(sql);
			
			Iterator<WorkItem> it = workItemHash.iterator();
			while(it.hasNext())
			{
				WorkItem workItem = it.next();
				System.out.println("Adding Item "+workItem.getItemDescription()+"\n");
				session.save(workItem);
			}
			session.getTransaction().commit();
			session.flush();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}

	
	@Override
	public void deleteRFIs()
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			Statement st=session.connection().createStatement();
			String sql="delete from rfi_approval where 1=1";
			st.execute(sql);
			sql="delete from rfi where 1=1";
			st.execute(sql);
			
			session.getTransaction().commit();
			session.flush();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void addRFIs(Set<RFI> rfiHash)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			Iterator<RFI> it = rfiHash.iterator();
			int i = 0;
			while(it.hasNext())
			{
				++i;
				RFI rfi = it.next();
				System.out.println("Adding Rfi number "+i+"\n");
				session.save(rfi);
			}
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void editBoq(BOQ boq)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<BOQ> boqList = session.createCriteria(BOQ.class)
						.add(Restrictions.eq("boqNumber",boq.getBoqNumber() ))
						.list();
			BOQ originalBoq = boqList.get(0);
			originalBoq.setAmount(boq.getAmount());
			originalBoq.setDescription(boq.getDescription());
			originalBoq.setCategory(boq.getCategory());
			originalBoq.setCategorySequence(boq.getCategorySequence());
			originalBoq.setQuantity(boq.getQuantity());
			originalBoq.setRate(boq.getRate());
			originalBoq.setUnit(boq.getUnit());
			
			session.saveOrUpdate(originalBoq);
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void editQuantity(Quantity quantity)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Quantity> quantityList = session.createCriteria(Quantity.class)
						.add(Restrictions.eq("quantityId",quantity.getQuantityId() ))
						.list();
			Quantity originalQuantity = quantityList.get(0);
			originalQuantity.setFromChainage(quantity.getFromChainage());
			originalQuantity.setToChainage(quantity.getToChainage());
			originalQuantity.setActivity(quantity.getActivity());
			originalQuantity.setItemDescription(quantity.getItemDescription());
			originalQuantity.setLayer(quantity.getLayer());
			originalQuantity.setSide(quantity.getSide());
			originalQuantity.setQuantity(quantity.getQuantity());
			
			session.saveOrUpdate(originalQuantity);
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void editLayerExceptions(LayerException layerException)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<LayerException> layerExceptionsList = session.createCriteria(LayerException.class)
						.add(Restrictions.eq("exceptionId",layerException.getExceptionId() ))
						.list();
			LayerException originalLayerException = layerExceptionsList.get(0);
			originalLayerException.setFromChainage(layerException.getFromChainage());
			originalLayerException.setToChainage(layerException.getToChainage());
			originalLayerException.setActivity(layerException.getActivity());
			originalLayerException.setItemDescription(layerException.getItemDescription());
			originalLayerException.setLayer(layerException.getLayer());
			originalLayerException.setSide(layerException.getSide());
			
			session.saveOrUpdate(originalLayerException);
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void deleteQuantity(Quantity quantity)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Quantity> quantityList = session.createCriteria(Quantity.class)
					.add(Restrictions.eq("quantityId",quantity.getQuantityId() ))
					.list();
			Quantity originalQuantity = quantityList.get(0);
			
			session.delete(originalQuantity);
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void deleteBoq(BOQ boq)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<BOQ> boqList = session.createCriteria(BOQ.class)
						.add(Restrictions.eq("boqNumber",boq.getBoqNumber() ))
						.list();
			BOQ originalBoq = boqList.get(0);
			
			session.delete(originalBoq);
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
	}
		
	
	@Override
	public void edit(RFI rfi) {
	
		
		Session session = getSession();
		try
		{
			File file = new File("C:\\Users\\Abhishek\\Desktop\\rfiBill.txt");
			try{
			FileWriter fos = new FileWriter(file);
			
			//fos.write("\n\n\n\n Size of bill is "+rfi.getRfiBills().size()+"\n\n\n\n");
			fos.close();
			}
			catch( Exception e){
				e.printStackTrace();
				
			}
				session.beginTransaction();
				List<RFI> rfiList = session.createCriteria(RFI.class)
						.add(Restrictions.eq("rfiNumber",rfi.getRfiNumber() ))
						.list();
				RFI originalRfi = rfiList.get(0);
				System.out.println("Original rfi is "+originalRfi.getRfiNumber()+"\n");
				originalRfi.setRfiCode(rfi.getRfiCode());
				originalRfi.setFromChainage(rfi.getFromChainage());
				originalRfi.setToChainage(rfi.getToChainage());
				originalRfi.setSide(rfi.getSide());
				originalRfi.setLayer(rfi.getLayer());
				originalRfi.setActivity(rfi.getActivity());
				originalRfi.setItemDescription(rfi.getItemDescription());
				originalRfi.setWiRemarks(rfi.getWiRemarks());
				originalRfi.setRemarks(rfi.getRemarks());
				originalRfi.setBreakRfiString("");
				originalRfi.setStatus(rfi.getStatus());
				originalRfi.setGrade(rfi.getGrade());
				originalRfi.setIssueDate(rfi.getIssueDate());
				originalRfi.setInspectionDate(rfi.getInspectionDate());
				//originalRfi.setBillNumber(rfi.getBillNumber());
				originalRfi.setRfiApprovals(rfi.getRfiApprovals());
				originalRfi.setLastEditedByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
				/*
				System.out.println("\n\n\n\n Size of bill is "+rfi.getRfiBills().size()+"\n\n\n\n");
				Iterator<RFIBill> bill = rfi.getRfiBills().iterator();
				try {
				while(bill.hasNext())
				{
					//fos.write("\n\n\n\n Bill number is "+bill.next().getBillNumber()+"\n\n\n\n");
					System.out.println("\n\n\n\n Bill number is "+bill.next().getBillNumber()+"\n\n\n\n");
					
				}
				
				//fos.close();
				}
				catch (Exception e) {
					e.printStackTrace();
					
				}
				*/
				originalRfi.setRfiBills(rfi.getRfiBills());
				session.saveOrUpdate(originalRfi);
				session.getTransaction().commit();
				session.flush();
		}
		finally
		{
			session.close();
		}
		
		
		
	}
	
	@Override
	public List<RFI> getRfiList()
	{
		Session session = getSession();
		try
		{
			List<RFI> rfiList = session.createCriteria(RFI.class).list();
			session.flush();
			return rfiList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<WorkItem> getWorkItemsList()
	{
		Session session = getSession();
		try
		{
			//List<WorkItem> workItemsList = session.createCriteria(WorkItem.class).addOrder(Property.forName("sequence").asc()).addOrder( Property.forName("boq_numbe").desc() ).list();
			List<WorkItem> workItemsList = session.createSQLQuery("select * from work_item order by sequence, convert(boq_number,DECIMAL(10,5)), boq_number, item_description").addEntity(WorkItem.class).list();
			session.flush();
			return workItemsList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<ExpandedRFI> getExpandedRfiList()
	{
		Session session = getSession();
		try
		{
			List<ExpandedRFI> expandedRfiList = session.createSQLQuery("select * from rfi_all_details_view").addEntity(ExpandedRFI.class).list();
			session.flush();
			return expandedRfiList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public synchronized Integer getExpandedRfiListSize(String whereClause)
	{
		Session session = getSession();
		try
		{
			List<BigInteger> rfiCountList = session.createSQLQuery("select count(*) from  rfi_all_details_view "+whereClause).list();
			BigInteger rfiCount = rfiCountList.get(0);
			
			session.flush();
			return Integer.valueOf(rfiCount.intValue());
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public Integer getLayerExceptionsListSize(String whereClause)
	{
		Session session = getSession();
		try
		{
			List<BigInteger> layerExceptionsList = session.createSQLQuery("select count(*) from  layer_exceptions "+whereClause).list();
			BigInteger layerExceptionCount = layerExceptionsList.get(0);
			
			session.flush();
			return Integer.valueOf(layerExceptionCount.intValue());
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<LayerException> getLayerExceptionsList(int posStart, int count, String orderBy, String where) {
		
		Session session = getSession();
		try
		{
			List<LayerException> layerExceptionsList = null;
			if (count == 0)
			{
				System.out.println("select * from rfi_all_details_view "+where+orderBy+" ");
				layerExceptionsList = session.createSQLQuery("select * from layer_exceptions "+where+ orderBy+" ").addEntity(LayerException.class).list();
			}
			else
			{
				System.out.println("select * from rfi_all_details_view "+where+orderBy+" limit "+posStart+","+count);
				layerExceptionsList = session.createSQLQuery("select * from layer_exceptions "+where+orderBy+" limit "+posStart+","+count).addEntity(LayerException.class).list();
			}
			session.flush();
			return layerExceptionsList;
		}
		finally
		{
			session.close();
		}
	
	}
	
	@Override
	public synchronized List<ExpandedRFI> getExpandedRfiList(int posStart, int count, String orderBy, String where) {
		
		Session session = getSession();
		try
		{
			List<ExpandedRFI> expandedRfiList = null;
			if (count == 0)
			{
				System.out.println("select * from rfi_all_details_view "+where+orderBy+" ");
				expandedRfiList = session.createSQLQuery("select * from rfi_all_details_view "+where+ orderBy+" ").addEntity(ExpandedRFI.class).list();
			}
			else
			{
				System.out.println("select * from rfi_all_details_view "+where+orderBy+" limit "+posStart+","+count);
				expandedRfiList = session.createSQLQuery("select * from rfi_all_details_view "+where+orderBy+" limit "+posStart+","+count).addEntity(ExpandedRFI.class).list();
			}
			session.flush();
			return expandedRfiList;
		}
		finally
		{
			session.close();
		}
	
	}
	
	@Override
	public synchronized List<ExpandedRFI> getExpandedRfiBillList(int posStart, int count, String orderBy, String where) {
		
		Session session = getSession();
		try
		{
			List<ExpandedRFI> expandedRfiList = null;
			if (count == 0)
			{
				System.out.println("select * from rfi_bill_details_view "+where+orderBy+" ");
				expandedRfiList = session.createSQLQuery("select * from rfi_bill_details_view "+where+ orderBy+" ").addEntity(ExpandedRFI.class).list();
			}
			else
			{
				System.out.println("select * from rfi_bill_details_view "+where+orderBy+" limit "+posStart+","+count);
				expandedRfiList = session.createSQLQuery("select * from rfi_bill_details_view "+where+orderBy+" limit "+posStart+","+count).addEntity(ExpandedRFI.class).list();
			}
			session.flush();
			return expandedRfiList;
		}
		finally
		{
			session.close();
		}
	
	}
	
	public static final String rfi_bill_details_select = "select "
			+"a.rfi_number AS rfi_number,a.activity AS activity,"
			+"a.bill_number AS bill_number,"
			+"d.bill_from AS approved_from,"
			+"d.bill_to AS approved_to,"
			+"d.bill_side AS side,"
			+"a.layer AS layer,"
			+"a.item_description AS item_description,"
			+"d.payable_quantity AS quantity "
			+"from "
			+"(rfi.rfi a inner join rfi.rfi_bill d on (a.rfi_number = d.rfi_number)) ";
		
	
	@Override
	public synchronized List<ExpandedRFI> getDeductionRfiBillList(String orderBy, String where) {
		
		Session session = getSession();
		try
		{
			
			List<Object[]> objectList = null;
			List<ExpandedRFI> expandedRfiList = new ArrayList<ExpandedRFI>();
			System.out.println(rfi_bill_details_select+where+orderBy+" ");
			objectList = session.createSQLQuery(rfi_bill_details_select+where+orderBy+" ").list();
			
			if (objectList != null)
			{
				Iterator<Object[]> it = objectList.iterator();
				while (it.hasNext())
				{
					Object[] object = it.next();
					String rfiNumber = (String) object[0];
					String activity = (String) object[1];
					String billNumber = (String) object[2];
					Integer approvedFrom = (Integer) object[3];
					Integer approvedTo = (Integer) object[4];
					String side = (String) object[5];
					String layer = (String) object[6];
					String itemDescription = (String) object[7];
					Double quantity = (Double) object[8];
					
					
					ExpandedRFI expRfi = new ExpandedRFI();
					expRfi.setRfiNumber(rfiNumber);
					expRfi.setActivity(activity);
					expRfi.setBillNumber(billNumber);
					expRfi.setApprovedFrom(approvedFrom);
					expRfi.setApprovedTo(approvedTo);
					expRfi.setApprovedSide(side);
					expRfi.setApprovedLayer(layer);
					expRfi.setItemDescription(itemDescription);
					expRfi.setQuantity(quantity);
					
					expandedRfiList.add(expRfi);
					
				}

			}
			session.flush();
			return  expandedRfiList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<String> getLayersForWorkItem(String itemDescription)
	{
		
		Session session = getSession();
		try
		{
			List<String> layersList = session.createSQLQuery("select b.layer from work_item a , work_item_layers b where a.work_item_id = b.work_item_id and a.item_description = '"+itemDescription+"' order by convert(b.layer,SIGNED) desc").list();
			if (layersList.contains("Top"))
			{
				layersList.remove("Top");
				layersList.add(0, "Top");
			}
			
			session.flush();
			return layersList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<String> getItemsInSequence(String[] items)
	{
		
		if (items != null)
		 {
			
			Session session = getSession();
			try
			{
				StringBuffer itemQuery = new StringBuffer("");
		 		for (int j=0; j<items.length; ++j)
		 		{
		 			String itemDescription = items[j];
		 			
		 			if(j == 0)
					 {
						 itemQuery.append("'"+itemDescription+"'");
					 }
					 else
					 {
						 itemQuery.append(",'"+itemDescription+"'");
					 }
		 		}
		 		
		 		List<String> itemsList = session.createSQLQuery("select item_description from work_item where item_description in ("+itemQuery.toString()+") order by sequence desc").list();
		 		session.flush();
				return itemsList;
			 }
			finally
			{
				session.close();
			}
		 }
		return null;
	}
	
	@Override
	public List<Object[]> getTopLayerTableEntry(String item, int from, int to, String side)
	{
		
		Session session = getSession();
		try
		{
			List<Object[]> topLayerList = session.createSQLQuery("select from_chainage, to_chainage, layer from layer_exceptions where item_description = '"+item+"' and ((from_chainage >= "+from+" and from_chainage < "+to+") or (to_chainage >= "+from+" and to_chainage < "+to+")) and side = '"+side+"'").list();
			session.flush();
			return topLayerList;
		}
		finally
		{
			session.close();
		}
		
		
	}
	
	@Override
	public void addBarChart(BarChart barchart)
	{
		
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			session.save(barchart);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public List<String> getStatusBarChartNames()
	{
		
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<String> statusBarChartNames = session.createSQLQuery("select barchart_name from barchart where username = '"+SecurityContextHolder.getContext().getAuthentication().getName()+"' order by barchart_name").list();
			
			session.getTransaction().commit();
			session.flush();
			return statusBarChartNames;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public BarChart getStatusBarChart(String barChartName) {
		
		Session session = getSession();
		try
		{
			List<BarChart> barChartList = session.createCriteria(BarChart.class)
					.add(Restrictions.eq("barChartName",barChartName ))
					.add(Restrictions.eq("userName",SecurityContextHolder.getContext().getAuthentication().getName() ))
					.list();
			BarChart barChart = barChartList.get(0);
			return barChart;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void addLayerException(LayerException layerException)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			session.save(layerException);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public void deleteLayerExceptions(Long exceptionId)
	{
		
		
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<LayerException> layerExceptionsList = session.createCriteria(LayerException.class)
					.add(Restrictions.eq("exceptionId",exceptionId ))
					.list();
			LayerException originalLayerException = layerExceptionsList.get(0);
			session.delete(originalLayerException);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
	}
	
	
	@Override
	public void deleteQuantity(Long quantityId)
	{
		
		
		Session session = getSession();
		try
		{
			session.beginTransaction();
			
			List<Quantity> quantityList = session.createCriteria(Quantity.class)
					.add(Restrictions.eq("quantityId",quantityId ))
					.list();
			Quantity originalQuantity = quantityList.get(0);
			session.delete(originalQuantity);
			
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public Integer getQuantityListSize(String whereClause) {
		
		Session session = getSession();
		try
		{
			List<BigInteger> quantityList = session.createSQLQuery("select count(*) from  quantity "+whereClause).list();
			BigInteger layerExceptionCount = quantityList.get(0);
			
			session.flush();
			return Integer.valueOf(layerExceptionCount.intValue());
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public List<Quantity> getQuantityList(int posStart, int count, String orderBy, String where) {
		
		Session session = getSession();
		try
		{
			List<Quantity> quantityList = null;
			if (count == 0)
			{
				System.out.println("select * from quantity "+where+orderBy+" ");
				quantityList = session.createSQLQuery("select * from quantity "+where+ orderBy+" ").addEntity(Quantity.class).list();
			}
			else
			{
				System.out.println("select * from quantity "+where+orderBy+" limit "+posStart+","+count);
				quantityList = session.createSQLQuery("select * from quantity "+where+orderBy+" limit "+posStart+","+count).addEntity(Quantity.class).list();
			}
			session.flush();
			return quantityList;
		}
		finally
		{
			session.close();
		}
		
	
	}
	
	@Override
	public Boolean isBillUnderPreparation()
	{
		
		
		Session session = getSession();
		try
		{
			List<Boolean> booleanList = session.createSQLQuery("select prepare_bill from  prepare_bill ").list();
			Boolean isBillUnderPrep = booleanList.get(0);
			
			session.flush();
			return isBillUnderPrep;
		}
		finally
		{
			session.close();
		}
		
		
		
	}
	
	@Override
	public List<Object[]> getPreparedItems()
	{
		Session session = getSession();
		try
		{
			List<Object[]> preparedItemsList = session.createSQLQuery("select distinct activity, item_description,approved_side,approved_layer from rfi_all_details_view where status='Approved' and bill_number='Under Preparation' order by convert(boq_number,DECIMAL(10,5)) asc, boq_number asc, approved_side asc, approved_layer desc").list();
			session.flush();
			return preparedItemsList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<Object[]> getItemsForRFINumbers(Set rfiNumberSet, String billNumber)
	{
		Session session = getSession();
		try
		{
			String rfiNum = (String)rfiNumberSet.iterator().next();
			session.beginTransaction();
			List<Object[]> preparedItemsList = session.createSQLQuery("select distinct activity, item_description,approved_side,approved_layer from rfi_all_details_view where rfi_number in (:list) order by convert(boq_number,DECIMAL(10,5)) asc, boq_number asc, approved_side asc, approved_layer desc").setParameterList("list", rfiNumberSet).list();
			session.createSQLQuery("update rfi set bill_number = 'Under Edition' where rfi_number in (:list)").setParameterList("list", rfiNumberSet).executeUpdate();
			session.getTransaction().commit();
			return preparedItemsList;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<String> getPreviousBilledItems(String billNumber)
	{
		Session session = getSession();
		try
		{
			List<String> preparedItemsList = session.createSQLQuery("select distinct item_description from rfi_all_details_view where bill_number='"+billNumber+"' order by convert(boq_number,DECIMAL(10,5)) asc, boq_number asc, approved_side asc, approved_layer desc").list();
			session.flush();
			return preparedItemsList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void createNewBill(Bill bill, LinkedHashMap itemMap) throws Exception
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			session.save(bill);
			//session.createSQLQuery("insert into bill values ('"+bill.getBillNumber()+"', '"+bill.getBillFromDate()+"', '"+bill.getBillToDate()+"', '"+bill.getBillDate()+"')").executeUpdate();
			
			StringBuffer query = new StringBuffer();
			Iterator itemMapIterator = itemMap.entrySet().iterator();
			int billEntryFound = 0;
			while (itemMapIterator.hasNext())
			{
				
				
				Map.Entry<String, LinkedHashMap> itemEntry = (Map.Entry<String, LinkedHashMap>)itemMapIterator.next();
				String item = itemEntry.getKey();
				LinkedHashMap activityMap = itemEntry.getValue();
				Iterator activityMapIterator = activityMap.entrySet().iterator();
				while (activityMapIterator.hasNext())
				{
					Map.Entry<String, LinkedHashMap> activityEntry = (Map.Entry<String, LinkedHashMap>)activityMapIterator.next();
					String activity = activityEntry.getKey();
				
					LinkedHashMap sideMap = activityEntry.getValue();
					
					
					Iterator sideMapIterator = sideMap.entrySet().iterator();
					while (sideMapIterator.hasNext())
					{
						Map.Entry<String, LinkedHashMap> sideEntry = (Map.Entry<String, LinkedHashMap>)sideMapIterator.next();
						String side = sideEntry.getKey();
						LinkedHashMap layerMap = sideEntry.getValue();
						
						Iterator layerMapIterator = layerMap.entrySet().iterator();
						while (layerMapIterator.hasNext())
						{
							Map.Entry<String, List<RFIBill>> layerEntry = (Map.Entry<String, List<RFIBill>>)layerMapIterator.next();
							String layer = layerEntry.getKey();
							List<RFIBill> rfiBillList = layerEntry.getValue();
			
							Iterator<RFIBill> it = rfiBillList.iterator();
							if(it.hasNext())
							{
								while (it.hasNext())
								{
									RFIBill rfiBill = it.next();
									rfiBill.setBillNumber(bill.getBillNumber());
									//query.append("insert into rfi_bill values ('"+rfiBill.getRfiNumber()+"', '"+rfiBill.getBillSide()+"', '"+rfiBill.getBillFrom()+"', '"+rfiBill.getBillTo()+"', '"+rfiBill.getQuantity()+"', '"+rfiBill.getRate()+"', '"+rfiBill.getAmount()+"');");
									session.save(rfiBill);
									billEntryFound = 1;
								}
								
							}
						}
					}
				}
			}
    	
	    	if (billEntryFound == 1)
	    	{
	    		//session.createSQLQuery(query.toString()).executeUpdate();
	    	}
			
			session.createSQLQuery("update rfi set bill_number =  '"+bill.getBillNumber()+"' where bill_number = 'Under Preparation'").executeUpdate();
			session.getTransaction().commit();
		}
		catch (Exception ex)
		{
			session.getTransaction().rollback();
			ex.printStackTrace();
			throw ex;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void editPreviousBill(String billNumber, LinkedHashMap itemMap, List rfiNumberList) throws Exception
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			//session.save(bill);
			//session.createSQLQuery("insert into bill values ('"+bill.getBillNumber()+"', '"+bill.getBillFromDate()+"', '"+bill.getBillToDate()+"', '"+bill.getBillDate()+"')").executeUpdate();
			
			StringBuffer query = new StringBuffer();
			Iterator itemMapIterator = itemMap.entrySet().iterator();
			int billEntryFound = 0;
			while (itemMapIterator.hasNext())
			{
				
				
				Map.Entry<String, LinkedHashMap> itemEntry = (Map.Entry<String, LinkedHashMap>)itemMapIterator.next();
				String item = itemEntry.getKey();
				LinkedHashMap activityMap = itemEntry.getValue();
				Iterator activityMapIterator = activityMap.entrySet().iterator();
				while (activityMapIterator.hasNext())
				{
					Map.Entry<String, LinkedHashMap> activityEntry = (Map.Entry<String, LinkedHashMap>)activityMapIterator.next();
					String activity = activityEntry.getKey();
				
					LinkedHashMap sideMap = activityEntry.getValue();
					
					
					Iterator sideMapIterator = sideMap.entrySet().iterator();
					while (sideMapIterator.hasNext())
					{
						Map.Entry<String, LinkedHashMap> sideEntry = (Map.Entry<String, LinkedHashMap>)sideMapIterator.next();
						String side = sideEntry.getKey();
						LinkedHashMap layerMap = sideEntry.getValue();
						
						Iterator layerMapIterator = layerMap.entrySet().iterator();
						while (layerMapIterator.hasNext())
						{
							Map.Entry<String, List<RFIBill>> layerEntry = (Map.Entry<String, List<RFIBill>>)layerMapIterator.next();
							String layer = layerEntry.getKey();
							List<RFIBill> rfiBillList = layerEntry.getValue();
			
							Iterator<RFIBill> it = rfiBillList.iterator();
							if(it.hasNext())
							{
								while (it.hasNext())
								{
									RFIBill rfiBill = it.next();
									rfiBill.setBillNumber(billNumber);
									//query.append("insert into rfi_bill values ('"+rfiBill.getRfiNumber()+"', '"+rfiBill.getBillSide()+"', '"+rfiBill.getBillFrom()+"', '"+rfiBill.getBillTo()+"', '"+rfiBill.getQuantity()+"', '"+rfiBill.getRate()+"', '"+rfiBill.getAmount()+"');");
									session.save(rfiBill);
									billEntryFound = 1;
								}
								
							}
						}
					}
				}
			}
    	
	    	if (billEntryFound == 1)
	    	{
	    		//session.createSQLQuery(query.toString()).executeUpdate();
	    	}
	    	session.createSQLQuery("update rfi set bill_number = '"+billNumber+"' where rfi_number in (:list)").setParameterList("list", rfiNumberList).executeUpdate();
			session.getTransaction().commit();
		}
		catch (Exception ex)
		{
			session.getTransaction().rollback();
			ex.printStackTrace();
			throw ex;
		}
		finally
		{
			session.close();
		}
	}
	
	
	@Override
	public List<Bill> getBills()
	{
		
		Session session = getSession();
		List<Bill> billList = null;
		try{
			
			billList = session.createCriteria(Bill.class).list();
			session.flush();
			return billList;
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public List<RFIBill> getRfiBillList(String billNumber, String item)
	{
		Session session = getSession();
		List<RFIBill> billList = new ArrayList<RFIBill>();
		
		try{
			
			List<Object[]> objectList = session.createSQLQuery("select bill.payable_quantity, bill.deduct_quantity, bill.rfi_number, bill.bill_from, bill.bill_to, bill.bill_side, bill.quantity, bill.rate, bill.amount, rfi.activity, rfi.layer, rfi.issue_date from (rfi_bill bill inner join rfi rfi on (rfi.rfi_number = bill.rfi_number)) where rfi.item_description = '"+item+"' AND bill.bill_number = '"+billNumber+"'").list();
			if (objectList != null)
			{
				Iterator<Object[]> it = objectList.iterator();
				while (it.hasNext())
				{
					Object[] object = it.next();
					Double payableQuantity = (Double) object[0];
					Double deductQuantity = (Double) object[1];
					String rfiNumber = (String) object[2];
					Integer billFrom = (Integer) object[3];
					Integer billTo = (Integer) object[4];
					String billSide = (String) object[5];
					Double quantity = (Double) object[6];
					Double rate = (Double) object[7];
					Double amount = (Double) object[8];
					String activity = (String) object[9];
					String layer = (String) object[10];
					Date issueDate = (Date) object[11];
					
					RFIBill rfiBill = new RFIBill();
					rfiBill.setBillNumber(billNumber);
					rfiBill.setItemDescription(item);
					rfiBill.setRfiNumber(rfiNumber);
					rfiBill.setBillFrom(billFrom);
					rfiBill.setBillTo(billTo);
					rfiBill.setBillActivity(activity);
					rfiBill.setBillLayer(layer);
					rfiBill.setBillSide(billSide);
					rfiBill.setQuantity(quantity);
					rfiBill.setRate(rate);
					rfiBill.setAmount(amount);
					rfiBill.setPayableQuantity(payableQuantity);
					rfiBill.setDeductQuantity(deductQuantity);
					rfiBill.setIssueDate(issueDate);
					
					
					billList.add(rfiBill);
										
				}
				
			}
			session.flush();
			return  billList;
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public Bill getBill(String billNumber)
	{
		Session session = getSession();
		try
		{
			List<Bill> billList = session.createCriteria(Bill.class)
					.add(Restrictions.eq("billNumber",billNumber ))
					.list();
			Bill originalBill = billList.get(0);
			
			session.flush();
			return originalBill;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public List<ExpandedRFI> isOverlap(RFI rfi)
	{
		boolean isOverlap = false;
		List<ExpandedRFI> expandedRfiList = null;
		List<ExpandedRFI> expandedRfiList1 = null;
		List<ExpandedRFI> expandedRfiList2 = null;
		ExpandedRFI overlappedRfi = null;
		Session session = getSession();
		try
		{
			String sideQuery = null;
			if (rfi.getSide().trim().equalsIgnoreCase("LHS") || rfi.getSide().trim().equalsIgnoreCase("RHS"))
			{
				sideQuery = "('"+rfi.getSide()+"', 'BS')";
			}
			else if(rfi.getSide().trim().equalsIgnoreCase("BS"))
			{
				sideQuery = "('LHS', 'RHS', 'BS')";
			}
			else
			{
				sideQuery = "('"+rfi.getSide()+"')";
			}
			expandedRfiList1 = session.createSQLQuery("select * from rfi_all_details_view where status= 'Approved' AND activity = '"+rfi.getActivity()+"' AND item_description = '"+rfi.getItemDescription()+"' AND approved_side IN "+sideQuery+" AND approved_layer = '"+rfi.getLayer()+"' AND ((approved_from >= "+rfi.getFromChainage()+" and approved_from < "+rfi.getToChainage()+" ) or (approved_to > "+rfi.getFromChainage()+" and approved_to <= "+rfi.getToChainage()+") or (approved_from <=  "+rfi.getFromChainage()+" and approved_to >= "+rfi.getToChainage()+"))").addEntity(ExpandedRFI.class).list();
			if(expandedRfiList1 != null && expandedRfiList1.size() > 0)
			{
				overlappedRfi  = expandedRfiList1.get(0);
				if(overlappedRfi != null)
				{
					isOverlap = true;
					expandedRfiList = expandedRfiList1; 
				}
			}
			
			expandedRfiList2 = session.createSQLQuery("select * from rfi_all_details_view where status= 'In Process' AND activity = '"+rfi.getActivity()+"' AND item_description = '"+rfi.getItemDescription()+"' AND side IN "+sideQuery+" AND layer = '"+rfi.getLayer()+"' AND ((from_chainage >= "+rfi.getFromChainage()+" and from_chainage < "+rfi.getToChainage()+" ) or (to_chainage > "+rfi.getFromChainage()+" and to_chainage <= "+rfi.getToChainage()+") or (from_chainage <=  "+rfi.getFromChainage()+" and to_chainage >= "+rfi.getToChainage()+"))").addEntity(ExpandedRFI.class).list();
				if(expandedRfiList2 != null && expandedRfiList2.size() > 0)
				{
					overlappedRfi  = expandedRfiList2.get(0);
					if(overlappedRfi != null)
					{
						isOverlap = true;
						if(expandedRfiList != null)
						{
							expandedRfiList.addAll(expandedRfiList2);
						}
						else
						{
							expandedRfiList = expandedRfiList2;
						}
					}
				}
			session.flush();
			
			return expandedRfiList;
			
		}
		finally
		{
			session.close();
		}
		
	}
	
	@Override
	public List<UserRegistrationForm> getUsersList()
	{
		Session session = getSession();
		List<UserRegistrationForm> userList = new ArrayList<UserRegistrationForm>();
		
		try{
			
			List<Object[]> objectList = session.createSQLQuery("select user.username, user.first_name, user.last_name, authorities.authority from (users user inner join authorities authorities on (user.username = authorities.username)) where user.enabled = b'1' and  user.username <> '"+SecurityContextHolder.getContext().getAuthentication().getName()+"' order by authorities.authority, user.username").list();
			if (objectList != null)
			{
				Iterator<Object[]> it = objectList.iterator();
				while (it.hasNext())
				{
					Object[] object = it.next();
					String username = (String) object[0];
					String firstName = (String) object[1];
					String lastName = (String) object[2];
					String role = (String) object[3];
					
					
					UserRegistrationForm user = new UserRegistrationForm();
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setUserName(username);
					user.setRole(role);
					
					
					userList.add(user);
										
				}
				
			}
			session.flush();
			return  userList;
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void deleteUser(String username)
	{
		Session session = getSession();
		try
		{
			session.beginTransaction();
			session.createSQLQuery("delete from authorities where username = '"+username+"'").executeUpdate();
			session.createSQLQuery("delete from users where username = '"+username+"'").executeUpdate();
			session.getTransaction().commit();
			session.flush();
		}
		finally
		{
			session.close();
		}
	}
	

}
