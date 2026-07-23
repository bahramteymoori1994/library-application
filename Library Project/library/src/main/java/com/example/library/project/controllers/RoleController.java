package com.example.library.project.controllers;

import com.example.library.project.dto.requests.RoleRequestDto;
import com.example.library.project.dto.responses.RoleResponseDto;
import com.example.library.project.services.interfaces.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/saveRole")
    public String save(@ModelAttribute("roleDto") RoleRequestDto roleRequestDto, RedirectAttributes redirectAttributes) throws Exception {

        try{

            roleService.save(roleRequestDto);
            redirectAttributes.addFlashAttribute("message", "نقش کاربر با موفقیت ثبت شد");
        }
        catch(Exception exception){
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت");
        }

        return "redirect:/role";
    }

    @PutMapping("/updateRole")
    public RoleResponseDto update(@RequestBody RoleRequestDto roleRequestDto) throws Exception {
        return roleService.update(roleRequestDto);
    }

    @GetMapping("/findRoleById/{id}")
    public RoleResponseDto findRoleById(@PathVariable Long id) throws Exception {
        return roleService.findById(id);
    }

    @GetMapping("/findAllRoles")
    public List<RoleResponseDto> findAllRoles(){
        return roleService.findAll();
    }

    @GetMapping()
    public String getAllRoles(Model model){

        List<RoleResponseDto> roles = findAllRoles();

        model.addAttribute("roles", roles);
        model.addAttribute("roleDto", new RoleRequestDto());

        return "role";
    }
}