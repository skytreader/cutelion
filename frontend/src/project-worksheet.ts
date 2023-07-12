import { html } from 'lit';
import { BaseTemplate } from "Frontend/src/base-template";
import { Project } from "Frontend/src/data-model";

class ProjectWorksheet extends BaseTemplate {
    private project?: Project;
    pageContent() {
        return html`
        <h2>Edit Project</h2>
        `
    }
}

customElements.define("project-worksheet", ProjectWorksheet)