import { Dashboard } from "./dashboard.model";

export class User {
    firstName: String;
    lastName: String;
    email: String;
    password: String;
    notesDashboard: Dashboard;
    archiveDashboard: Dashboard;
    trashDashboard: Dashboard;
    notesId: number;
    archiveId: number;
    trashId: number;

    constructor(inter: UserInterface = {}) {
        this.firstName = inter.firstName;
        this.lastName = inter.lastName;
        this.email = inter.email;
        this.password = inter.password;
        this.notesDashboard = inter.notesDashboard;
        this.archiveDashboard = inter.archiveDashboard;
        this.trashDashboard = inter.trashDashboard;
    }
}

interface UserInterface {
    firstName?: String;
    lastName?: String;
    email?: String;
    password?: String;
    notesDashboard?: Dashboard;
    archiveDashboard?: Dashboard;
    trashDashboard?: Dashboard;
    notesId?: number;
    archiveId?: number;
    trashId?: number;
}