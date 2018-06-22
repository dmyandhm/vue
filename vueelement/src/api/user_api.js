import * as Config from './index';

export default {
  //登录
  login: params => {
    return Config.POST('/api/users/login', params) //url为服务端地址
  },
}
