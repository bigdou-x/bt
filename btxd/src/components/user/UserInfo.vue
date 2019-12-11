<template>
  <div>
    <span class="user-title">用户信息</span>
    <el-row type="flex" justify="start" style="margin-bottom:10px;">
      <el-col :lg="1">
        <el-button type="primary" size="small">添加</el-button>
      </el-col>
      <el-col :lg="2">
        <el-button type="primary" size="small" @click="deleteUsers">删除</el-button>
      </el-col>
    </el-row>
    <el-table
      :data="userInfo"
      border
      @selection-change="handleSelectionChange"
      v-loading="tableLoading"
    >
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="nickName" label="姓名"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column prop="phoneNum" label="电话"></el-table-column>
      <el-table-column label="操作" fixed="right">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="deleteUser(scope.row)">删除</el-button>
          <el-button type="text" size="small" @click="editUser(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="提示" :visible.sync="dialogVisible" :before-close="handleClose" width="500px">
      <div slot="title">
        <span style="float:left">编辑用户</span>
      </div>
      <el-form :model="form">
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.nickName" autocomplete="off" size="medium" style="width:300px"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth">
          <el-input v-model="form.email" autocomplete="off" size="medium" style="width:300px"></el-input>
        </el-form-item>
        <el-form-item label="地址" :label-width="formLabelWidth">
          <el-input v-model="form.address" autocomplete="off" size="medium" style="width:300px"></el-input>
        </el-form-item>
        <el-form-item label="电话" :label-width="formLabelWidth">
          <el-input v-model="form.phoneNum" autocomplete="off" size="medium" style="width:300px"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formLabelWidth: "50px",
      dialogVisible: false,
      form: {
        nickName: "",
        email: "",
        address: "",
        phoneNum: ""
      },
      userInfo: [],
      selectedItem: [],
      tableLoading: false
    };
  },
  mounted: function() {
    this.queryData();
  },
  methods: {
    queryData() {
      this.tableLoading = true;
      this.getRequest("/user").then(resp => {
        if (resp && resp.status == 200) {
          this.userInfo = resp.data;
        }
        this.tableLoading = false;
      });
    },
    handleClose() {
      this.$confirm("确认关闭？")
        .then(result => {
          this.dialogVisible = false;
        })
        .catch(err => {});
    },
    deleteUser(row) {
      this.$confirm("确认删除？").then(result => {
        this.doDelete(row.id);
      });
    },
    deleteUsers() {
      this.$confirm("确认删除？").then(result => {
        let ids = "";
        for (let i = 0; i < this.selectedItem.length; i++) {
          ids += this.selectedItem[i].id + ",";
        }
        this.doDelete(ids);
      });
    },
    doDelete(ids) {
      this.deleteRequest("/user/" + ids).then(resp => {
        if (resp && resp.status == 200) {
          let data = resp.data;

          this.$message({ type: "success", message: data.msg });
          this.queryData();
        }
      });
    },
    handleSelectionChange(val) {
      this.selectedItem = val;
    },
    editUser(item) {
      this.form.nickName = item.nickName;
      this.form.address = item.address;
      this.form.email = item.email;
      this.form.phoneNum = item.phoneNum;
      this.dialogVisible = true;
    }
  }
};
</script>

<style>
.user-title {
  display: flex;
  justify-content: space-between;
  padding: 3px;
  font-size: 18px;
}
</style>