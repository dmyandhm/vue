import {POST} from './index';

export default {
  // 获取菜单
  getMenu: params => {
    return POST('/api/menu', params) //url为服务端地址
  },
}
