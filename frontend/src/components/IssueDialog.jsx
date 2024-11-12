import { useState } from "react";
import {
  Dialog,
  DialogHeader,
  DialogBody,
  DialogFooter,
} from "@material-tailwind/react";
import UserForm from "./UserForm";
import IssueRecordForm from "./IssueRecordForm";

export default function IssueDialog({ open, setOpen, onSubmit, selectedRecord, setSelectedRecord, errorMessage }) {

  const handleOpen = () => setOpen(!open);

  return (
    <>
      <Dialog size="xl" open={open} handler={handleOpen}>
        <DialogHeader className="text-primary">Add User</DialogHeader>
        <DialogBody>
          <IssueRecordForm
            onSubmit={onSubmit}
            selectedRecord={selectedRecord}
            setSelectedRecord={setSelectedRecord}
          />
          <div className="text-red-600 flex justify-center">{errorMessage}</div>
        </DialogBody>
        <DialogFooter></DialogFooter>
      </Dialog>
    </>
  );
}
