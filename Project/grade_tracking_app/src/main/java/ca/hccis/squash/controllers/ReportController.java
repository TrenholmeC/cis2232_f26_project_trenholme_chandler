package ca.hccis.squash.controllers;

import ca.hccis.squash.bo.ItemBO;
import ca.hccis.squash.bo.SkillsAssessmentSquashTechnicalBO;
import ca.hccis.squash.dao.SkillsAssessmentSquashTechnicalDAO;
import ca.hccis.squash.entity.ItemTypeAverage;
import ca.hccis.squash.entity.ReportItem;
import ca.hccis.squash.entity.ReportSquash;
import ca.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Controller to administer reports of the project.
 *
 * @author BJM
 * @since 20251009
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    /**
     * Send the user to list of reports view.
     *
     * @param model
     * @param session
     * @return To the appropriate view
     * @author BJM
     * @since 20251009
     */
    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        //BJM 20200602 Issue#1 Set the current date in the session
        logger.info("Running the reports controller base method");
        return "report/list";
    }

    /**
     * Method to send user to the athlete/assessor name report.
     *
     * @param model
     * @return view for list
     * @author BJM
     * @since 2025-10-06
     */
    @RequestMapping("/squash/name")
    public String reportSquashName(Model model) {
        logger.info("Running the reports controller squash name method");
        model.addAttribute("reportInput",new ReportSquash());
        return "report/reportSquashName";
    }

    /**
     * Method to send user to the score name report.
     *
     * @param model
     * @return view for list
     * @author BJM
     * @since 2025-10-09
     */
    @RequestMapping("/squash/score")
    public String reportSquashScore(Model model) {
        logger.info("Running the reports controller squash score method");
        model.addAttribute("reportInput",new ReportSquash());
        return "report/reportSquashScore";
    }


    /**
     * Process the report - name
     *
     * @param model
     * @param reportSquash Object containing inputs for the report
     * @return view to show report
     * @author BJM
     * @since 2025-10-06
     */
    @RequestMapping("/squash/name/submit")
    public String reportSquashNameSubmit(Model model, @ModelAttribute("reportInput") ReportSquash reportSquash) {

        System.out.println("Name from input form:"+reportSquash.getName());

        //Write some model code to go to the db and get the appropriate assessments
        //Add them to a collection in the ReportSquash class
        SkillsAssessmentSquashTechnicalBO skillsAssessmentSquashTechnicalBO = new SkillsAssessmentSquashTechnicalBO();
        ArrayList<SkillsAssessmentSquashTechnical> theList = skillsAssessmentSquashTechnicalBO.processSelectAllByAthleteAssessorName(reportSquash.getName());
        reportSquash.setSkillsAssessmentSquashTechnicals(theList);

        //Add a message in case the report does not contain any data
        if (theList != null && theList.isEmpty()) {
            model.addAttribute("message", "No assessments found for that name");
            System.out.println("BJM - no data found");
        }

        //Put object in model so it can be used on the view (html)
        model.addAttribute("reportInput", reportSquash);

        return "report/reportSquashName"; //Send user to another view.
    }

    /**
     * Process the report - name
     *
     * @param model
     * @param reportSquash Object containing inputs for the report
     * @return view to show report
     * @author BJM
     * @since 2025-10-06
     */
    @RequestMapping("/squash/score/submit")
    public String reportSquashScoreSubmit(Model model, @ModelAttribute("reportInput") ReportSquash reportSquash) {

        System.out.println("Min from input form:"+reportSquash.getMinScore());

        //Write some model code to go to the db and get the appropriate assessments
        //Add them to a collection in the ReportSquash class
        SkillsAssessmentSquashTechnicalDAO squashDAO = new SkillsAssessmentSquashTechnicalDAO();
        ArrayList<SkillsAssessmentSquashTechnical> theList = squashDAO.selectAllByScoreMinMax(reportSquash.getMinScore(), reportSquash.getMaxScore());
        reportSquash.setSkillsAssessmentSquashTechnicals(theList);

        //Add a message in case the report does not contain any data
        if (theList != null && theList.isEmpty()) {
            model.addAttribute("message", "No assessments found for that name");
            System.out.println("BJM - no data found");
        }

        //Put object in model so it can be used on the view (html)
        model.addAttribute("reportInput", reportSquash);

        return "report/reportSquashScore"; //Send user to another view.
    }

    /**
     * Method to send user to the item type average report.
     *
     * @param model
     * @return view for list
     * @author TrenholmeC
     * @since 2026-10-30
     */
    @RequestMapping("/item/typeAverage")
    public String reportItemTypeAverage(Model model) {
        logger.info("Running the reports controller item type average method");
        model.addAttribute("reportInput", new ReportItem());
        return "report/reportItemTypeAverage";
    }

    /**
     * Process the report - average mark per item type for a given class name.
     *
     * @param model
     * @param reportItem Object containing inputs for the report
     * @return view to show report
     * @author TrenholmeC
     * @since 2026-10-30
     */
    @RequestMapping("/item/typeAverage/submit")
    public String reportItemTypeAverageSubmit(Model model, @ModelAttribute("reportInput") ReportItem reportItem) {

        System.out.println("Class name from input form:" + reportItem.getClassName());

        //Write some model code to go to the db and get the average mark per item type
        //Add them to a collection in the ReportItem class
        ItemBO itemBO = new ItemBO();
        ArrayList<ItemTypeAverage> theList = itemBO.processAverageMarkByTypeForClassName(reportItem.getClassName());
        reportItem.setItemTypeAverages(theList);

        //Add a message in case the report does not contain any data
        if (theList != null && theList.isEmpty()) {
            model.addAttribute("message", "No gradable items found for that class name");
            System.out.println("BJM - no data found");
        }

        //Put object in model so it can be used on the view (html)
        model.addAttribute("reportInput", reportItem);

        return "report/reportItemTypeAverage"; //Send user to another view.
    }

}
