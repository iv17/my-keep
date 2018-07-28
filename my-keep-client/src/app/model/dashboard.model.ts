import { Widget } from "./widget.model";

export class Dashboard {
    widgets: Array<Widget>;

    constructor(inter: DashboardInterface = {}) {
        this.widgets = inter.widgets;
    }
}

interface DashboardInterface {
    widgets?: Array<Widget>;
}