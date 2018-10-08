import * as Config from './index';

export default {
  //登录
  login: params => {
    return Config.POST('/api/user/login', params) //url为服务端地址
  },
  //退出登录
  loginout:params => {
    return Config.POST('/api/user/loginout', params)
  },
  //管理员列表
  getAdminList:params => {
    return Config.POST('/api/userManager/userTable', params)
  },
}
