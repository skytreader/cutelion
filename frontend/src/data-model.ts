export interface Translation {
    key: String;
    value: String;
}

export interface Project {
    id: number;
    name: string;
    defaultLanguage: string;
    createdAt: Date;
    modifiedAt: Date;
    translations: Translation[];
}