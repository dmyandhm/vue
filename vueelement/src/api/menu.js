import {POST} from './index';

export default {
  //登录
  getMenu: params => {
    return POST('/api/menu', params) //url为服务端地址
  },
}
