import { Component } from '@angular/core';
import { Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
@Component({
  selector: 'app-compte-modification',
  templateUrl: './compte-modification.component.html',
  styleUrl: './compte-modification.component.css'
})
export class CompteModificationComponent {
  constructor(
    public dialogRef: MatDialogRef<CompteModificationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
