import { useState } from "react";
import {
  Button,
  Dialog,
  DialogHeader,
  DialogBody,
  DialogFooter,
} from "@material-tailwind/react";
import UserForm from "./UserForm";

export default function DialogComp({ open, setOpen, onSubmit, selectedUser, setSelectedUser }) {

  const handleOpen = () => setOpen(!open);

  return (
    <>
      <Dialog size="xl" open={open} handler={handleOpen}>
        <DialogHeader className="text-primary">Add User</DialogHeader>
        <DialogBody>
          <UserForm onSubmit={onSubmit} selectedUser={selectedUser} setSelectedUser={setSelectedUser} />
        </DialogBody>
      </Dialog>
    </>
  );
}
