package com.model.rfi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
 
@SuppressWarnings("deprecation") 
public class LayerController extends SimpleFormController { 
 
private RFIService rfiService;

 
public LayerController() { 
setCommandClass(LayerForm.class); 
setCommandName("layer"); 
} 
public void setRfiService(RFIService rfiService) { 
this.rfiService = rfiService; 
} 

protected Map referenceData(HttpServletRequest request) throws Exception {
	System.out.println("\n\n\n\n\nInlayer the reference data     \n");
	Map referenceData = new HashMap();
	List<String> layerList = rfiService.getLayerList();
	layerList.remove("-");
	layerList.remove("Top");
	referenceData.put("layerList", layerList);
	return referenceData; 
}


@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
try {
	System.out.println("\n\n\n\n\nInlayer the submit method     \n");
	LayerForm layerForm = (LayerForm) command;
	Layer layer = new Layer();
	layer.setLayer(layerForm.getLayer());
	if (layerForm != null)
	{
		String actionType = request.getParameter("actionType");
		if("add".equals(actionType))
		{
			rfiService.addLayer(layer);
		}
		else if ("edit".equals(actionType))
		{
			rfiService.editLayer(layerForm.getLayer(), layerForm.getNewLayer());
		}
		else if ("delete".equals(actionType))
		{
			rfiService.deleteLayer(layer);
		}
	}
	return new ModelAndView("redirect:layer.htm");
}

catch (Exception e) {
	System.out.println("skdjaldj     \n");
	e.printStackTrace();
	throw e;
}

} 
}