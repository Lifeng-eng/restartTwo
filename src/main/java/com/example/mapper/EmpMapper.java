package com.example.mapper;

import com.example.pojo.dto.EmpExpr;
import com.example.pojo.dto.EmployeeQueryDTO;
import com.example.pojo.dto.Expr;
import com.example.pojo.vo.EmployeeVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {
    List<EmployeeVO> empPageQuery(@Param("query") EmployeeQueryDTO query, @Param("offset")int offset, @Param("limit") int limit);

    long countEmployees(@Param("query") EmployeeQueryDTO query);

    void empDelete(@Param("ids")List<Integer> ids);

    //插入员工信息
    void insertEmp(EmpExpr empExpr);

    //插入工作经历
    void insertEmpExpr(Expr empExpr);

    @Select("select * from emp where id = #{id}")
    EmployeeVO findById(Integer id);

    @Select("SELECT * from emp_expr where id = #{id}")
    List<Expr> empExprSelect(Integer id);

    @Delete("delete from emp_expr where emp_id = #{id}")
    void empExprDelete(Integer id);

    @Select("SELECT * from emp")
    List<EmployeeVO> getEmpList();
}
