package com.model.rfi;

import java.io.File;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.springmvc.rfi.UserRegistrationForm;

public interface RFIService {
	
	public RFI add (RFI rfi);
	public void edit (RFI rfi);
	public List<String> getItemsList();
	public List<String> getSideList();
	public List<String> getLayerList();
	public List<String> getBillList();
	public List<String> getRfiCodeList();
	public List<RFI> getRfiList();
	public List<RFI> getRfiAllDetailsList();
	public List<String> getStatusList();
	public List<String> getGradeList();
	
	public Map<String, List<BOQ>> getBoqList();
	public List<String> getCategoryList();
	public List<String> getUnitsList();
	
	public void addBoq(BOQ boq);
	public void editBoq(BOQ boq);
	public BOQ getBoq(String boqNumber);
	public List<String> getBoqNumberList();
	public void deleteBoq(BOQ boq);
	public void addBoqList(Set<BOQ> boqHash);
	
	public void editStatus(String oldStatus, String newStatus);
	public void addStatus(Status status);
	public void deleteStatus(Status status);
	
	public void editGrade(String oldGrade, String newGrade);
	public void addGrade(Grade grade);
	public void deleteGrade(Grade grade);
	
	public void editRFICode(String oldRFICode, String newRFICode);
	public void addRFICode(RFICode rfiCode);
	public void deleteRFICode(RFICode rfiCode);
	
	public void editSide(String oldSide, String newSide);
	public void addSide(Side side);
	public void deleteSide(Side side);
	
	public void editLayer(String oldLayer, String newLayer);
	public void addLayer(Layer layer);
	public void deleteLayer(Layer layer);
	
	public List<WorkItem> getWorkItemsList();
	public List<String> getWorkItemDescriptionList();
	public void addWorkItem(WorkItem workItem);
	public void editWorkItem(WorkItem workItem);
	public void deleteWorkItem(WorkItem workItem);
	public WorkItem getWorkItem(String itemDescription);
	
	public void addBarChart(BarChart barchart);
	

	public void getStatusPercentages(List<RFI> rfiList, Map map);
	
	public Integer getNextRfiNumber();
	public BigInteger getStatusCount(String status, String where);
	
	public List<ExpandedRFI> getExpandedRfiList();
	public List<ExpandedRFI> getExpandedRfiList(int posStart, int count, String orderBy, String where);
	public Integer getExpandedRfiListSize(String whereClause);
	
	public File uploadRFIFile(File rfiFile);
	
	public RFI getRFI(String rfiNumber);
	
	public List<ExpandedRFI> getMultipleRFI(String[] rfiNumberList);
	
	public void createNewUser(String role, String username, String firstName, String lastName, String password);
	
	public List<String> getWorkItemsDescription(String where);
	
	public List<String> getBoqNumberList(String where);
	
	public List<String> getLayersForWorkItem(String itemDescription);
	
	public List<String> getItemsInSequence(String[] items);
	
	public List<Object[]> getTopLayerTableEntry(String item, int from, int to, String side);
	
	public List<String> getStatusBarChartNames();
	
	public BarChart getStatusBarChart(String barChartName);
	
	public void addLayerException(LayerException layerException);
	
	public Integer getLayerExceptionsListSize(String whereClause);
	
	public List<LayerException> getLayerExceptionsList(int posStart, int count, String orderBy, String where);
	
	public void deleteLayerExceptions(Long exceptionId);
	
	public void addQuantity(Quantity quantity);
	
	public void editQuantity(Quantity quantity);
	
	public void deleteQuantity(Quantity quantity);
	
	public Integer getQuantityListSize(String whereClause);
	
	public List<Quantity> getQuantityList(int posStart, int count, String orderBy, String where);
	
	public Boolean isBillUnderPreparation();
	
	public void changeMultipleRFIBillNumber(String[] rfiNumberList, String billNumber);
	
	public List<Object[]> getPreparedItems();
	
	public Quantity getQuantity(Long quantityId);
	
	public void addQuantityList(Set<Quantity> quantityHash);
	
	public List<Layer> getLayerObjectsList();
	
	public List<Side> getSideObjectsList();
	
	public void deleteQuantity(Long quantityId);
	
	public void editLayerExceptions(LayerException layerException);
	
	public LayerException getLayerException(Long exceptionId);
	
	public void addLayerExceptionList(Set<LayerException> layerExceptionHash);
	
	public void deleteActivity(Activity activity);
	public void editActivity(String oldActivity, String newActivity);
	public List<String> getActivityList();
	public void addActivity(Activity activity);
	public List<String> getActivityWorkItemsDescription(String where);
	public WorkItem getActivityWorkItem(String itemDescription, String activity);
	public List<String> getActivityBoqNumberList(String where);
	
	public List<Activity> getActivityObjectsList();
	
	public LinkedHashMap prepareBill(String viewString) throws Exception;
	
	public void createNewBill(Bill bill, LinkedHashMap itemMap) throws Exception;
	
	public List<String> getPreviousBilledItems(String billNumber);
	
	public List<Bill> getBills();
	
	public List<RFIBill> getRfiBillList(String billNumber, String item);
	
	public Bill getBill(String billNumber);
	
	public LinkedHashMap preparePreviousBill(Set rfiNumberSet, String billNumber) throws Exception;
	
	public void editPreviousBill(String billNumber, LinkedHashMap itemMap, List rfiNumberList) throws Exception;
	
	public void deleteRFIFromBill(String[] rfiNumberList);
	
	public List<ExpandedRFI> isOverlap(RFI rfi);
	
	public List<ExpandedRFI> getExpandedRfiBillList(int posStart, int count, String orderBy, String where);
	
	public List<Object[]> getItemsForRFINumbers(Set rfiNumberSet, String billNumber);
	
	public LinkedHashMap formItemMap(List<Object[]> preparedItemsList);
	
	public List<UserRegistrationForm> getUsersList();
	
	public void deleteUser(String username);
	
	public List<ExpandedRFI> getDeductionRfiBillList(String orderBy, String where);
	
}


