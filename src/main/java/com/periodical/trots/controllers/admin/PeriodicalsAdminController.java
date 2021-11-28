package com.periodical.trots.controllers.admin;

import com.periodical.trots.entities.*;
import com.periodical.trots.services.PeriodicalHasSubjectService;
import com.periodical.trots.services.PeriodicalService;
import com.periodical.trots.services.PublisherService;
import com.periodical.trots.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class PeriodicalsAdminController {

    @Autowired
    private PeriodicalService periodicalService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private PeriodicalHasSubjectService periodicalHasSubjectService;

    @GetMapping("/periodicals")
    public String periodicalsPageForAdmin(Model model) {
        model.addAttribute("periodicals", periodicalService.getAllPeriodicals());
        model.addAttribute("publisherList", publisherService.findAll());
        model.addAttribute("subjectList", subjectService.findAll());
        model.addAttribute("periodicalForm", new PeriodicalEntity());
        return "PeriodicalPageForAdmin";
    }

    @GetMapping("/update-periodical")
    public String updatePeriodicalForAdmin(Model model, @RequestParam("id") Integer id){
        PeriodicalHasSubjectEntity periodicalHasSubject = new PeriodicalHasSubjectEntity();
        model.addAttribute("publisherList", publisherService.findAll());
        model.addAttribute("subjectList", subjectService.findAll());
        model.addAttribute("periodicalForm", periodicalService.getPeriodicalById(id));
        return "UpdatePeriodicalPageForAdmin";
    }

    @PostMapping("/update-periodical")
    public String updatePeriodical(@ModelAttribute("periodicalForm") PeriodicalEntity periodicalForm,
                                @RequestParam("publisherName") String publisherName,
                                @RequestParam("publisherTelephone") String publisherTelephone,
                                @RequestParam("subject") List<String> subjectListFromWeb,
                                @RequestParam("file") MultipartFile file,
                                @RequestParam("periodicalId") Integer periodicalId) {


        return "redirect:/periodicals";
    }



    @PostMapping("/delete-periodical")
    public String deletePeriodicalById(@RequestParam("periodicalId") Integer id) {
        periodicalService.deletePeriodical(id);
        return "redirect:/periodicals";
    }

    @PostMapping("/add-periodical")
    public String addPeriodical(@ModelAttribute("periodicalForm") PeriodicalEntity periodicalForm,
                                @RequestParam("publisherName") String publisherName,
                                @RequestParam("publisherTelephone") String publisherTelephone,
                                @RequestParam("subject") List<String> subjectListFromWeb,
                                @RequestParam("file") MultipartFile file) {
        PublisherEntity publisherEntity;
        PeriodicalHasSubjectEntity periodicalHasSubject = new PeriodicalHasSubjectEntity();
        PeriodicalHasSubjectEntityId periodicalHasSubjectEntityId = new PeriodicalHasSubjectEntityId();

        List<PublisherEntity> publisherEntityList = publisherService.findAll();
        publisherEntity = publisherEntityList.stream().filter(publisherEntity1 ->
                publisherName.equals(publisherEntity1.getName())).findAny().orElse(null);

        if (publisherEntity == null) {
            publisherEntity = new PublisherEntity();
            publisherEntity.setName(publisherName);
            publisherEntity.setTelephoneNumber(publisherTelephone);
            publisherService.save(publisherEntity);
        }
        periodicalForm.setPublisher(publisherEntity);

        try {
            String filename = file.getOriginalFilename();
            periodicalForm.setImages(filename);
            File path = new File("C:\\Users\\Dima\\Desktop\\periodicalsSpring\\src\\main\\resources\\static\\images\\" + filename);
            path.createNewFile();
            FileOutputStream output = new FileOutputStream(path);
            output.write(file.getBytes());
            output.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        int periodicalId = periodicalService.addPeriodical(periodicalForm);
        int subjectId;
        SubjectEntity subjectEntity;
        List<SubjectEntity> subjectEntities = subjectService.findAll();
        for (String s : subjectListFromWeb) {
            if (!s.equals("")){
            subjectEntity = subjectEntities.stream().filter(subjectEntity1 ->
                    s.equals(subjectEntity1.getThemesOfBooks())).findAny().orElse(null);
            if (subjectEntity == null){
                subjectEntity = new SubjectEntity();
                subjectEntity.setThemesOfBooks(s);
                subjectId = subjectService.save(subjectEntity);
            }
            subjectId = subjectEntity.getId();
            periodicalHasSubjectEntityId.setPeriodicalId(periodicalId);
            periodicalHasSubjectEntityId.setSubjectId(subjectId);
            periodicalHasSubject.setId(periodicalHasSubjectEntityId);
            periodicalHasSubjectService.save(periodicalHasSubject);
            }
        }

        return "redirect:/periodicals";
    }

}
