import { Dashboard } from "./dashboard.model";

export class UpdateRequest {
    notesDashboard: Dashboard;
    archiveDashboard: Dashboard;

    constructor(inter: UpdateRequestInterface = {}) {
        this.notesDashboard = inter.notesDashboard;
        this.archiveDashboard = inter.archiveDashboard;
     
    }
}

interface UpdateRequestInterface {
    notesDashboard?: Dashboard;
    archiveDashboard?: Dashboard;
}