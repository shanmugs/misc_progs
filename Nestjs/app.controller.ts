import { Body, Controller, Delete, Get, Param, Post, Put } from '@nestjs/common';
import { AppService } from './app.service';
import { Todo } from './todo.entity';

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get('todos')
  async getTodos(): Promise<Todo[]> {
    return this.appService.getTodos();
  }

  @Post('todos')
  async createTodo(@Body() todo: Todo): Promise<Todo> {
    return this.appService.createTodo(todo);
  }

  @Put('todos/:id')
  async updateTodo(@Param('id') id: number, @Body() todo: Todo): Promise<Todo> {
    return this.appService.updateTodo(id, todo);
  }

  @Delete('todos/:id')
  async deleteTodo(@Param('id') id: number): Promise<void> {
    await this.appService.deleteTodo(id);
  }
}
