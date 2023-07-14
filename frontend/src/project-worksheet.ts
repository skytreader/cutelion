import { html } from 'lit';
import { BaseTemplate } from "Frontend/src/base-template";
import { Project } from "Frontend/src/data-model";

class ProjectWorksheet extends BaseTemplate {
    private project?: Project;
    pageContent() {
        const hasProject = this.project !== undefined;
        const headerText = hasProject ? "Edit Project" : "New Project";
        return html`
        <h2>${headerText}</h2>
        <div>
        <label>Name: <input type="text" id="project-name" value="${this.project?.name}"></label>
        <label>Default language: <input type="text" id="default-language" placeholder="en-US" value="${this.project?.defaultLanguage}"></label> 
        <button id="create-project">Create Project</button>
</div>
        `
    }
}

customElements.define("project-worksheet", ProjectWorksheet)