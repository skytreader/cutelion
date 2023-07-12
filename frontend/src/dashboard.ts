import { html } from 'lit';
import {BaseTemplate} from "Frontend/src/base-template";

class Dashboard extends BaseTemplate {
    private projects: Array<any> = [];

    pageContent() {
        const body = this.projects.length == 0 ? html`
         <p>No projects found. <button id="start-project">Start one?</button></p>
         ` :
         html`
         <p>Some projects found placeholder.</p>
         `
        return html`
        <h2>Project Dashboard</h2>
        ${body}
        `
    }
}

customElements.define("dashboard-page", Dashboard);