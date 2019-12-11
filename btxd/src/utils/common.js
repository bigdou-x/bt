import Vue from "vue";

export const initMenu = (router, store) => {
  if (store.state.routes.length > 0) {
    return;
  }
  Vue.axios.get("/menu/getAllMenus").then(resp => {
    var fmtRoutes = formatRoutes(resp.data);
    router.addRoutes(fmtRoutes);
    store.commit("initMenu", fmtRoutes);
  });
};

export const formatRoutes = routes => {
  let fmtRoutes = [];
  routes.forEach(route => {
    let { path, component, name, iconCls, children } = route;
    if (children && children instanceof Array) {
      children = formatRoutes(children);
    }

    let fmtRoute = {
      path: path,
      component(resolve) {
        require(["../components/" + component + ".vue"], resolve);
      },
      name: name,
      iconCls: iconCls,
      children: children
    };
    fmtRoutes.push(fmtRoute);
  });
  return fmtRoutes;
};
let base = "";
export const postRequest = (url, params) => {
  return Vue.axios({
    method: "post",
    url: `${base}${url}`,
    data: params,
    transformRequest: [
      function(data) {
        let ret = "";
        for (let it in data) {
          ret +=
            encodeURIComponent(it) + "=" + encodeURIComponent(data[it]) + "&";
        }
        return ret;
      }
    ],
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  });
};
export const uploadFileRequest = (url, params) => {
  return Vue.axios({
    method: "post",
    url: `${base}${url}`,
    data: params,
    headers: {
      "Content-Type": "multipart/form-data"
    }
  });
};
export const putRequest = (url, params) => {
  return Vue.axios({
    method: "put",
    url: `${base}${url}`,
    data: params,
    transformRequest: [
      function(data) {
        let ret = "";
        for (let it in data) {
          ret +=
            encodeURIComponent(it) + "=" + encodeURIComponent(data[it]) + "&";
        }
        return ret;
      }
    ],
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  });
};
export const deleteRequest = url => {
  return Vue.axios({
    method: "delete",
    url: `${base}${url}`
  });
};
export const getRequest = url => {
  return Vue.axios({
    method: "get",
    url: `${base}${url}`
  });
};
