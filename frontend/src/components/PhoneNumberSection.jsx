// components/PhoneNumberSection.js

import React from "react";

const PhoneNumberSection = ({ phoneNumbers, onPhoneChange, addPhoneField, removePhoneField }) => {
  return (
    <div>
      <label className="block font-medium text-gray-700">Phone Numbers</label>
      {phoneNumbers.map((phone, index) => (
        <div key={index} className="flex items-center mt-1">
          <input
            type="text"
            value={phone}
            onChange={(e) => onPhoneChange(index, e.target.value)}
            className="block w-full p-2 border border-gray-300 rounded-md mr-2 focus:ring focus:ring-green-500"
            placeholder={`Phone Number ${index + 1}`}
            required
          />
          {index > 0 && (
            <button
              type="button"
              onClick={() => removePhoneField(index)}
              className="text-red-500 hover:text-red-700"
            >
              Remove
            </button>
          )}
        </div>
      ))}
      <button
        type="button"
        onClick={addPhoneField}
        className="mt-2 text-green-600 hover:text-green-800"
      >
        + Add Phone
      </button>
    </div>
  );
};

export default PhoneNumberSection;
