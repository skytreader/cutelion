import { html, LitElement, HTMLTemplateResult } from 'lit';

export abstract class BaseTemplate extends LitElement {

    abstract pageContent(): HTMLTemplateResult;

    render() {
        return html`
        <div>
            <h1>CuteL10N</h1>
        </div>
        ${this.pageContent()}
        `;
    }

}