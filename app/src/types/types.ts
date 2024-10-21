export type Gender = 'M' | 'F';
export type GenderLabel = 'Male' | 'Female';
export type SelectOption = [ string, string ];
export type HTTPMethod = 'GET' | 'POST' | 'PATCH' | 'DELETE';
export type ResponseCode = 200 | 201 | 404 | 401
export type ResponseStatus = 200 | 201 | 404 | 401

export interface State {
  isPending: boolean;
  error: Error | null
}

export interface SignupForm {
  username: string | undefined;
  email: string | undefined;
  password: string | undefined;
  firstname: string | undefined;
  middlename: string | undefined;
  lastname: string | undefined;
  gender: Gender;
  birthdate: string | undefined;
}

export interface HTTPHeaderOptions {
  method: HTTPMethod;
  headers?: Record<string, string>
  body?: string
}

export interface User {
  username: string;
  email: string;
  firstname: string;
  middlename?: string;
  lastname: string;
  gender: GenderLabel;
  birthdate: string;
  dateRegistered: string;
}

export interface Email {
  idUser: string;
  emailUser: string;
  recepient: string;
  title: string;
  content: string;
  idThread: string | null;
  dateCreated: string;
  idEmail: string;
}

export interface DateTimeFormatOptions {
  month: 'long';
  day: 'numeric';
  year: 'numeric';
  hour?: 'numeric';
  minute?: 'numeric';
}

export interface ResponseUser {
  message: string;
  user: User;
  code: ResponseCode;
  status: ResponseStatus;
}

export interface ResponseEmail {
  message: string;
  emails: Email[];
  code: ResponseCode;
  status: ResponseStatus;
}

