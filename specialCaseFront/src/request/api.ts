
import instance from "./request";
 
//一般情况下，接口类型会放到一个文件
// 下面两个TS接口，表示要传的参数
interface ReqLogin {
    name: string
    paw: string
}
interface ReqStatus {
    id: string
    navStatus: string
}
 
 
// Res是返回的参数，T是泛型，需要自己定义，返回对数统一管理***
type Res<T> = Promise<ItypeAPI<T>>;
// 一般情况下响应数据返回的这三个参数，
// 但不排除后端返回其它的可能性，
interface ItypeAPI<T> {
    data: T,//请求的数据，用泛型
    message: string | null // 返回状态码的信息，如请求成功等
    code: string //返回后端自定义的200，404，500这种状态码
    errMessage: string
}
 
// 定义接口格式：
// export  const  自定义接口名  =  (形参:请求类型)：返回类型  =>  instance.方法(路径，后端要的参数);
// post请求 ，没参数
export const LogoutAPI = (): Res<null> => instance.post("/admin/logout");
 
// post请求，有参数,如传用户名和密码
export const loginAPI = (data: ReqLogin): Res<any> =>
    instance.post("/login/login", data);

// post请求，有参数,如传用户名和密码
export const registerAPI = (data: ReqLogin): Res<any> =>
instance.post("/login/registerUser", data);

// post请求，重置密码
export const resetAPI = (data: ReqLogin): Res<any> =>
instance.post("/login/resetPassword", data);

// post请求 ，没参数，但要路径传参
export const StatusAPI = (data: ReqStatus): Res<null> =>
    instance.post(`/productCategory?ids=${data.id}&navStatus=${data.navStatus}`);
 
 
//  get请求，没参数，
export const FlashSessionListApi = (): Res<null> =>
    instance.get("/flashSession/list");
 
// get请求，有参数，路径也要传参  (也可能直接在这写类型，不过不建议,大点的项目会维护一麻烦)
export const ProductCategoryApi = (params: { parentId: number }): Res<any> =>
    instance.get(`/productCategory/list/${params.parentId}`, { params });
 
// get请求，有参数，(如果你不会写类型也可以使用any,不过不建议,因为用了之后 和没写TS一样)
export const AdminListAPI = (params:any): Res<any> => instance.get("/admin/list", { params });
