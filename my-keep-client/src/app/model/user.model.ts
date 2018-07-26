import { Dashboard } from "./dashboard.model";

export class User {
    id: number;
    firstName: String;
    lastName: String;
    email: String;
    password: String;
    notesDashboard: Dashboard;
    archiveDashboard: Dashboard;
    trashBashboard: Dashboard;

    constructor(inter: UserInterface = {}) {
        this.id = inter.id;
        this.firstName = inter.firstName;
        this.lastName = inter.lastName;
        this.email = inter.email;
        this.password = inter.password;
        this.notesDashboard = inter.notesDashboard;
        this.archiveDashboard = inter.archiveDashboard;
        this.trashBashboard = inter.trashBashboard;
    }
}

interface UserInterface {
    id?: number;
    firstName?: String;
    lastName?: String;
    email?: String;
    password?: String;
    notesDashboard?: Dashboard;
    archiveDashboard?: Dashboard;
    trashBashboard?: Dashboard;
}