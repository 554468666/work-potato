<template>
  <el-form :model="form" :rules="rules" ref="form" label-width="120px" class="demo-ruleForm">
    <el-form-item label="标题" prop="title">
      <el-input v-model="form.title"></el-input>
    </el-form-item>
    <el-form-item label="内容" prop="content">
      <el-input type="textarea" v-model="form.content"></el-input>
    </el-form-item>
    <el-form-item label="图片">
      <el-upload
        class="avatar-uploader"
        action="/your/upload/api"
        :show-file-list="false"
        :on-success="handleSuccess"
        :before-upload="beforeAvatarUpload"
      >
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('form')">发布</el-button>
      <el-button @click="resetForm('form')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data () {
    var validateTitle = (rule, value, callback) => {
            if (!value) {
              return callback(new Error('请输入标题'))
            }
            setTimeout(() => {
              if (value.length < 5) {
                callback(new Error('标题长度不能小于 5 个字符'))
                callback(new Error('标题长度不能小于 5 个字符'))
              } else {
                callback()
              }
            }, 1000)
    }
    var validateContent = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入内容'))
        }
        setTimeout(() => {
          if (value.length < 10) {
            callback(new Error('内容长度不能小于 10 个字符'))
          } else {
            callback()
          }
        }, 1000)
    }
    return {
      form: {
        title: '',
        content: '',
        imageUrl: 'https://s2.loli.net/2024/04/20/' // 用于存储上传后的图片URL
      },
      rules: {
        title: [
          { validator: validateTitle, trigger: 'blur' }
        ],
        content: [
          { validator: validateContent, trigger: 'blur' }
        ]
      },
      imageUrl: '' // 初始为空，上传成功后设置
    }
  },
  methods: {
    beforeAvatarUpload (file) {
      // 上传图片前的钩子，可以在这里限制图片的大小、类型等
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传图片只能是 JPG/PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    handleSuccess (response, file, fileList) {
      // 上传成功后的钩子，假设服务器返回了图片的 URL
      this.imageUrl = response.data.url // 假设服务器返回的数据结构是 { url: '图片的URL' }
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 发送包含图片URL和其他表单数据的请求到后端
          // 这里只是一个示例，你需要根据实际情况来发送请求
          console.log('表单验证通过，可以发送数据到后端')
          // 假设 form.imageUrl 就是你要上传的图片的 URL
          // 使用 axios 或其他库发送 POST 请求到后端 API
        } else {
          console.log('表单验证失败')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
      this.imageUrl = '' // 重置图片 URL
    }
  }
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
