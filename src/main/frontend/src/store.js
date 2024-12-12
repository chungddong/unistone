import { create } from "zustand";

const useProjectStore = create((set) => ({
    selectedProject: null, // 선택된 프로젝트 데이터
    setSelectedProject: (project) => set({ selectedProject: project }),

    userName: "",
    setUserName: (name) => set({ userName: name }),

    userList: [], // 유저 리스트 초기 상태
    setUserList: (users) => set({ userList: users }),

    taskName: "",
    setTaskName: (name) => set({ taskName: name }),

    startDate: "",
    setStartDate: (date) => set({ startDate: date }),

    dueDate: "",
    setDueDate: (date) => set({ dueDate: date }),

    taskDescription: "",
    setTaskDescription: (description) => set({ taskDescription: description }),

    email: "",
    setEmail: (email) => set({ email: email }),

    statusNames: [], // 작업 상태 이름 목록
    setStatusNames: (names) => set({ statusNames: names }),

    statusIds: [], // 작업 상태 ID 목록
    setStatusIds: (ids) => set({ statusIds: ids }),

    tasks: {}, // 상태별 작업 데이터
    setTasks: (newTasks) => set({ tasks: newTasks }),
}));

export default useProjectStore;