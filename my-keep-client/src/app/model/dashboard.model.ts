import { Widget } from "./widget.model";
import { DashboardStatus } from "./dashboardStatus.model";

export class Dashboard {
    id: number;
    status: DashboardStatus;
    widgets: Array<Widget>;

    constructor(inter: DashboardInterface = {}) {
        this.id = inter.id;
        this.status = inter.status;
        this.widgets = inter.widgets;
       
    }
}

interface DashboardInterface {
    id?: number;
    status?: DashboardStatus;
    widgets?: Array<Widget>;
}