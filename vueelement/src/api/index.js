
import axios from 'axios'
//通用方法
let root='192.168.1.12:7001/';
export const POST = (url, params) => {
  return axios.post(`${root}${url}`, params).then(res => res.data);
}
