package com.xz.comtroller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuansc
 * @date 2019/3/28 0028 上午 11:01
 */
@RestController
@RequestMapping("sysDict")
@Api(value = "字典管理")
@Slf4j
public class SysDictController extends BaseController {

//    @Autowired
//    private ISysDict iSysDict;


//    @GetMapping("getSysDictByType")
//    @ApiOperation(value = "根据类型查询该类型下的字典值", notes = "根据类型查询该类型下的字典值")
//    public Result<List<SysDictList>> getSysDictByType(@RequestParam(required = true) @ApiParam(value = "类型") String key) {
//        log.info("SysDictController.getSysDictByType：" + JSON.toJSONString(key));
//
//        if (StringUtils.isBlank(key)) {
//            return new Result<>(ResultCode.PARAM.getCode(), "key为空。", null);
//        }
//        List<SysDictResponse> sysDictByType = iSysDict.getSysDictByType(key);
//        List<SysDictList> list = new ArrayList<>();
//        if (sysDictByType != null && !sysDictByType.isEmpty()) {
//            for (SysDictResponse sysDictResponse : sysDictByType) {
//                SysDictList bean = new SysDictList();
//                BeanUtils.copyProperties(bean, sysDictResponse);
//                list.add(bean);
//            }
//        }
//        return new Result<>(ResultCode.SUCCESS, list);
//    }
//
//
//    @GetMapping("getSysDictByAll")
//    @ApiOperation(value = "查询字典所有值", notes = "查询字典所有值")
//    public Result<Map<String,List<SysDictList>>> getSysDictByAll() {
//        log.info("SysDictController.getSysDictByAll");
//        List<SysDictResponse> sysDictByAll = iSysDict.getSysDictByAll();
//        Map<String,List<SysDictList>> map = new HashMap<>();
//        if (sysDictByAll != null && !sysDictByAll.isEmpty()) {
//            for (SysDictResponse sysDictResponse : sysDictByAll) {
//                List<SysDictList> list = map.get(sysDictResponse.getType());
//                if(list == null || list.isEmpty()){
//                    list = new ArrayList<>();
//                }
//                SysDictList bean = new SysDictList();
//                BeanUtils.copyProperties(bean, sysDictResponse);
//                list.add(bean);
//                map.put(sysDictResponse.getType(),list);
//            }
//        }
//        return new Result<>(ResultCode.SUCCESS, map);
//    }

}
