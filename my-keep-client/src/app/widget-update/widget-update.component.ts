import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

export interface DialogData {
  title: string;
  content: string;
}

@Component({
  selector: 'app-widget-update',
  templateUrl: './widget-update.component.html',
  styleUrls: ['./widget-update.component.css']
})
export class WidgetUpdateComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<WidgetUpdateComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}