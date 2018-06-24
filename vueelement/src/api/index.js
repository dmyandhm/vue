
import axios from 'axios'
//通用方法
export const POST = (url, params) => {
  return axios.post(url, params).then(res => res.data);
}
