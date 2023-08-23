export interface Translation {
    key: String;
    value: String;
    locale: String;
}

export interface Project {
    id: number;
    name: string;
    defaultLanguage: string;
    createdAt: Date;
    modifiedAt: Date;
    translations: Translation[];
}

export interface ProjectForDeletion {
    name: string;
}