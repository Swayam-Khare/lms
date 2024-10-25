import { useState } from "react";
import {
  Dialog,
  DialogHeader,
  DialogBody,
  DialogFooter,
} from "@material-tailwind/react";
import BookForm from "./BookForm";

export default function BookDialog({ open, setOpen, onSubmit, selectedBook, setSelectedBook, errorMessage }) {

  const handleOpen = () => setOpen(!open);

  return (
    <>
      <Dialog size="xl" open={open} handler={handleOpen}>
        <DialogHeader className="text-primary">
          {selectedBook ? "Edit Book" : "Add New Book"}
        </DialogHeader>
        <DialogBody>
          <BookForm
            onSubmit={onSubmit}
            selectedBook={selectedBook}
            setSelectedBook={setSelectedBook}
          />
          <div className="text-red-600 flex justify-center">{errorMessage}</div>
        </DialogBody>
        <DialogFooter></DialogFooter>
      </Dialog>
    </>
  );
}
