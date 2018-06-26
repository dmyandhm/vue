<template>
  <el-row class="warp">
    <el-col :span="24" class="warp-breadcrum">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }"><b>首页</b></el-breadcrumb-item>
        <el-breadcrumb-item>用户列表</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>

    <el-col :span="24" class="warp-main" v-loading="loading" element-loading-text="拼命加载中">
      <!--工具条-->
      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
        <el-form :inline="true" :model="filters">
          <el-form-item>
            <el-input v-model="filters.username" placeholder="账户" style="min-width: 240px;" @keyup.enter.native="handleSearch"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleSearch">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="showAddDialog">新增</el-button>
          </el-form-item>
        </el-form>
      </el-col>

      <!--列表-->
      <el-table :data="users" highlight-current-row v-loading="loading" style="width: 100%;">
        <el-table-column type="index" width="60">
        </el-table-column>
        <el-table-column prop="name" label="姓名"  sortable>
        </el-table-column>
        <el-table-column prop="username" label="账号"  sortable>
        </el-table-column>
        <el-table-column prop="balance" label="余额"   sortable>
        </el-table-column>
        <el-table-column prop="creditFacility" label="授信额度" sortable>
        </el-table-column>
        <el-table-column prop="createTime" label="添加时间" sortable>
        </el-table-column>
      </el-table>
      <!--分页-->
      <el-pagination class="warp-breadcrum" :style="{'text-align':'center'}" @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :page-sizes="[10, 20, 30, 50]" :page-size="limit"
                     layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>

      <!--新增界面-->
      <el-dialog title="新增" :visible.sync ="addFormVisible" :close-on-click-modal="false">
        <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="addForm.name" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="账号" prop="username">
            <el-input v-model="addForm.username" auto-complete="off"></el-input>
          </el-form-item>
          <!--<el-form-item label="日期">
            <el-date-picker type="date" placeholder="选择日期" v-model="addForm.publishAt"></el-date-picker>
          </el-form-item>
          <el-form-item label="简介" prop="description">
            <el-input type="textarea" v-model="addForm.description" :rows="8"></el-input>
          </el-form-item>-->
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click.native="addFormVisible = false">取消</el-button>
          <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
        </div>
      </el-dialog>

    </el-col>
  </el-row>
</template>

<script>
  import UserAPI from '../../api/user_api';

  export default {
    data() {
      return {
        filters: {
          username: '',
          name: ''
        },
        loading: false,
        users: [],
        roleType:2,
        total: 0,
        page: 1,
        limit: 10,

        //新增相关数据
        addFormVisible: false,//新增界面是否显示
        addLoading: false,
        addFormRules: {
          name: [
            {required: true, message: '请输入姓名', trigger: 'blur'}
          ],
          username: [
            {required: true, message: '请输入账户', trigger: 'blur'}
          ]
        },
        addForm: {
          name: '',
          username: ''
        }
      }
    },
    methods: {
      handleSearch(){
        this.total = 0;
        this.page = 1;
        this.search();
      },
      //获取用户列表
      search: function () {
        let that = this;
        let params = {
          pageNo: that.page,
          pageSize: that.limit,
          username: that.filters.username,
          roleType:that.roleType,
          name:that.filters.name
        };

        that.loading = true;
        UserAPI.getAdminList(params).then(function (result) {
          that.loading = false;
          if (result ) {
            that.total = result.total;
            that.users = result.rows;
          }
        }, function (err) {
          that.loading = false;
          that.$message.error({showClose: true, message: err.toString(), duration: 2000});
        }).catch(function (error) {
          that.loading = false;
          console.log(error);
          that.$message.error({showClose: true, message: '请求出现异常', duration: 2000});
        });
      },
      handleSizeChange(val) {
        this.limit=val;
      },
      handleCurrentChange(val) {
        this.page=val;
        this.search();
      },
      //显示新增页面
      showAddDialog: function () {
        this.addFormVisible = true;
//        this.addForm = {
//          name:
        this.$refs['a \'\',\n' +
        '//          username: \'\'\n' +
        '//        };ddForm'].resetFields();
      },
      //新增
      addSubmit: function () {
        let that = this;
        this.$refs.addForm.validate((valid) => {
          if (valid) {
//            that.loading = true;
            that.$message.success({showClose: true, message: '新增成功', duration: 2000});
            that.addFormVisible = false;
            that.handleSearch();

          }
        });
      },


    },
    mounted() {
      this.handleSearch()
    }
  }
</script>
