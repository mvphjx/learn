<template>
  <div>

    <el-table
      :data="tableData"
      style="width: 100%"
      @selection-change="selectItem">
      <el-table-column
        type="selection"
        width="50">
      </el-table-column>
      <el-table-column
        prop="s_name"
        label="所属组织架构">
      </el-table-column>
      <el-table-column
        label="用户名"
        prop="username"
        width="200">
      </el-table-column>
      <el-table-column
        label="备注"
        prop="remark"
        width="200">
      </el-table-column>
      <el-table-column
        label="状态"
        width="100">
      </el-table-column>
      <el-table-column
        label="操作"
        width="200">
      </el-table-column>
    </el-table>




    <div class="pos-rel p-t-20">
      <btnGroup :selectedData="multipleSelection" :type="'users'"></btnGroup>
      <div class="block pages">
        <el-pagination
          @current-change="handleCurrentChange"
          layout="prev, pager, next"
          :page-size="limit"
          :current-page="currentPage"
          :total="dataCount">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
  import http from '../../assets/js/http'
  export default {
    data() {
      return {
        tableData: [],
        dataCount: null,
        currentPage: null,
        realname: '',
        multipleSelection: [],
        limit: 1
      }
    },
    methods: {
      search() {
        router.push({ path: this.$route.path, query: { realname: this.realname, page: 1 }})
      },
      selectItem(val) {
        this.multipleSelection = val
      },
      handleCurrentChange(page) {
        router.push({ path: this.$route.path, query: { realname: this.realname, page: page }})
      },
      getAll() {
        this.loading = true
        const data = {
          params: {
            realname: this.realname,
            page: this.currentPage,
            rows: this.limit
          }
        }
        this.apiGet('api/video/list', data).then((res) => {
          console.log('res = ', _g.j2s(res))
          this.handelResponse(res, (data) => {
            this.tableData = data.data.list
            this.dataCount = data.data.total
          })
        })
      },
      getCurrentPage() {
        let data = this.$route.query
        if (data) {
          if (data.page) {
            this.currentPage = parseInt(data.page)
          } else {
            this.currentPage = 1
          }
        }
      },
      getRealname() {
        let data = this.$route.query
        if (data) {
          if (data.realname) {
            this.realname = data.realname
          } else {
            this.realname = ''
          }
        }
      },
      init() {
        this.getRealname()
        this.getCurrentPage()
        this.getAll()
      }
    },
    created() {
      this.init()
    },
    computed: {
      addShow() {
        return _g.getHasRule('users-save')
      },
      editShow() {
        return _g.getHasRule('users-update')
      },
      deleteShow() {
        return _g.getHasRule('users-delete')
      }
    },
    watch: {
      '$route' (to, from) {
        this.init()
      }
    },
    components: {
      btnGroup
    },
    mixins: [http]
  }
</script>
