package ru.teamname.projectname.controller.admin.pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.teamname.projectname.service.PackService;

@RestController
@RequestMapping(path = "/api/admin/pack")
public class PackController {

    @Autowired
    PackService packService;

}
