import { create } from "zustand";

const useProjectStore = create((set) => ({
    selectedProject: null, // 선택된 프로젝트 데이터
    setSelectedProject: (project) => set({ selectedProject: project }),

    userName: "",
    setUserName: (name) => set({ userName: name }),

    userList: [], // 유저 리스트 초기 상태
    setUserList: (users) => set({ userList: users }),

}));


export default useProjectStore;
