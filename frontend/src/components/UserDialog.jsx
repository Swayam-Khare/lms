import { useState } from "react";
import {
  Dialog,
  DialogHeader,
  DialogBody,
  DialogFooter,
} from "@material-tailwind/react";
import UserForm from "./UserForm";

export default function UserDialog({ open, setOpen, onSubmit, selectedUser, setSelectedUser, errorMessage }) {

  const handleOpen = () => setOpen(!open);

  return (
    <>
      <Dialog size="xl" open={open} handler={handleOpen}>
        <DialogHeader className="text-primary">Add User</DialogHeader>
        <DialogBody>
          <UserForm
            onSubmit={onSubmit}
            selectedUser={selectedUser}
            setSelectedUser={setSelectedUser}
          />
          <div className="text-red-600 flex justify-center">{errorMessage}</div>
        </DialogBody>
        <DialogFooter></DialogFooter>
      </Dialog>
    </>
  );
}
