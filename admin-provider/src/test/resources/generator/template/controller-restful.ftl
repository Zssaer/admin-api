package ${bussnessPackage}.controller;

import ${frameworkPackage}.result.Result;
import ${frameworkPackage}.result.ResultBuilder;
import ${frameworkPackage}.page.PageReq;
import com.admin.core.annotation.SysLog;
import ${basePackage}.model.${modelNameUpperCamel};
import ${bussnessPackage}.service.${modelNameUpperCamel}Service;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}s")
@Api(tags = "${modelNameUpperCamel}管理")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping
    @ApiOperation(value = "新增${modelNameLowerCamel}", notes = "新增${modelNameLowerCamel}")
    @SaCheckPermission("${modelNameLowerCamel}-post")
    @SysLog("新增${modelNameLowerCamel}")
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultBuilder.successResult();
    }

    @DeleteMapping
    @ApiOperation(value = "删除${modelNameLowerCamel}", notes = "删除${modelNameLowerCamel}")
    @SaCheckPermission("${modelNameLowerCamel}-delete")
    @SysLog("删除${modelNameLowerCamel}")
    public Result delete(@RequestParam(value = "ids") List<Integer> ids) {
    	Condition con = new Condition(${modelNameUpperCamel}.class);
    	con.createCriteria().andIn("id", ids);
        ${modelNameLowerCamel}Service.deleteByCondition(con);
        return ResultBuilder.successResult();
    }

    @PutMapping
    @ApiOperation(value = "更新${modelNameLowerCamel}", notes = "更新${modelNameLowerCamel}")
    @SaCheckPermission("${modelNameLowerCamel}-put")
    @SysLog("更新${modelNameLowerCamel}")
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultBuilder.successResult();
    }

    @GetMapping
    @ApiOperation(value = "获取${modelNameLowerCamel}列表", notes = "获取${modelNameLowerCamel}列表")
    @SaCheckPermission("${modelNameLowerCamel}-get")
    @SysLog("获取${modelNameLowerCamel}列表")
    public Result list(PageReq req) {
        PageHelper.startPage(req.getPage(), req.getSize());
        
        Condition con = new Condition(${modelNameUpperCamel}.class);
        Criteria cri = con.createCriteria();
        
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findByCondition(con);

        return ResultBuilder.successResult(new PageInfo<${modelNameUpperCamel}>(list));
    }
}
